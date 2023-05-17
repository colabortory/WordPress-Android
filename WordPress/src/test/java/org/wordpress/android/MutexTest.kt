package org.wordpress.android

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.Test
import kotlin.concurrent.thread

class MutexTest : CoroutineScope {
    override val coroutineContext = Dispatchers.IO + Job()

    @Test
    fun testMutex() {
        val job = launch {
            supervisorScope {
                repeat(10) {
                    launch {
                        MutexUser(it).start()
                    }
                }
            }
        }

        thread {
            while (job.isActive) {
                Thread.sleep(100L)
            }
            println("testMutex is finished!")
        }.join()
    }
}

@Suppress("TooGenericExceptionThrown")
class MutexUser(private val classId: Int) {
    private val mutex = Mutex()

    suspend fun start() = coroutineScope {
        println("MutexUser$classId Starting...")

        val parentScope = this
        launch {
            delay(200L)
            while (!mutex.isLocked) {
            }
            cancelAllTasks(parentScope)
        }

        repeat(5) { taskId ->
            delay(10L)
            println("MutexUser$classId Launching task $taskId")
            launch {
                println("MutexUser$classId Starting task $taskId")
                longRunningTask(taskId)
                println("MutexUser$classId Finished task $taskId (isActive=$isActive)")
            }
        }

        println("Done!")
    }

    private suspend fun cancelAllTasks(scope: CoroutineScope) {
        println("Cancelling all tasks...")
        scope.cancel()
    }

    private suspend fun longRunningTask(taskId: Int, shouldFail: Boolean = false) = coroutineScope {
        val taskName = "MutexUser$classId[Task $taskId]"

        try {
            mutex.withLock {
                println("$taskName: Mutex lock acquired, sleeping... (isActive=$isActive)")

//                async { Thread.sleep(1L) }.await()

                // use thread sleep because it's blocking and doesn't check coroutine cancellation, so it's close to
                // what we actually have inside Upload Starter
                Thread.sleep(1000L)

                if (shouldFail) {
                    println("$taskName: Failing!")
                    throw Exception("Task $taskId failed")
                }

                println("$taskName: Time to wake up! (isActive=$isActive)")
            }
        } catch (e: CancellationException) {
            println("$taskName: Exception caught: $e")

            println("$taskName: Rethrowing...")
            throw e
        }
    }
}
