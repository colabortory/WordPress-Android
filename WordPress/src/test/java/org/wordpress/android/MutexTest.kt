@file:Suppress("UnusedImports")
package org.wordpress.android

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.Test
import org.wordpress.android.ui.uploads.UploadStarter

@OptIn(DelicateCoroutinesApi::class)
@Suppress("GlobalCoroutineUsage")
class MutexTest {
    @Test
    fun testMutex() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println(
                "CoroutineExceptionHandler got $exception " +
                        "with suppressed ${exception.suppressed.contentToString()}"
            )
        }
        val job = GlobalScope.launch(Dispatchers.IO + handler) {
            MutexUser(0).start()
        }

        job.join()
        println("testMutex is finished!")
    }
}

@Suppress("TooGenericExceptionThrown")
class MutexUser(private val classId: Int) {
    private val mutex = Mutex()

    suspend fun start() = coroutineScope {
        println("MutexUser$classId Starting...")

        repeat(5) { taskId ->
            delay(10L)
            println("MutexUser$classId Launching task $taskId")
            launch {
                println("MutexUser$classId Starting task $taskId")
                longRunningTask(taskId, shouldFail = taskId == 2)
                println("MutexUser$classId Finished task $taskId (isActive=$isActive)")
            }
        }

        println("Done!")
    }

    private suspend fun longRunningTask(taskId: Int, shouldFail: Boolean = false) = coroutineScope {
        val taskName = "MutexUser$classId[Task $taskId]"

        try {
            runBlocking { }
            mutex.withLock {
                println("$taskName: Mutex lock acquired (isActive=$isActive)")

                /**
                 * async call to mimic the similar calls in the Upload Starter [UploadStarter.upload]
                 * The async/await operators are cancellable, so it's a bit different than having blocking code
                 * or even suspend functions without cancellation checks.
                 */
//                async { Thread.sleep(1000L) }.await()

                /**
                 * use thread sleep because it's blocking and doesn't check coroutine cancellation, so it's close to
                 * what we actually have happening inside [UploadStarter.upload]
                 */
                println("$taskName: Sleeping...")
                Thread.sleep(1000L)

                if (shouldFail) {
                    println("$taskName: Failing!")
                    throw Exception("Task $taskId failed")
                }

                println("$taskName: Time to wake up! (isActive=$isActive)")
            }
        } catch (e: CancellationException) {
            println("$taskName: CancellationException caught: $e")

            println("$taskName: Unlocking the mutex...")
            mutex.unlock()

            println("$taskName: Rethrowing...")
            throw e
        } catch (e: Exception) {
            println("$taskName: Exception caught: ${e.stackTraceToString()}}")

            println("$taskName: Rethrowing...")
            throw e
        }
    }
}
