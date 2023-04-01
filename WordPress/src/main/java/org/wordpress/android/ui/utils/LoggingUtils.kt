package org.wordpress.android.ui.utils


import android.content.Intent
import android.os.Bundle
import org.wordpress.android.util.AppLog

object LoggingUtils {

    @JvmStatic
    fun logIntent(intent: Intent?, label: String) {
        log("$label intent: $intent")

        intent?.run {
            data?.let {
                log("  data: $it")
            }
            extras?.let {
                log("  extras: [Bundle]")
                logNestedBundles(it, "    ")
            }
        }
    }

    private fun logNestedBundles(bundle: Bundle?, indent: String = "") {
        bundle?.keySet()?.forEach { key ->
            when (val value = bundle.get(key)) {
                !is Bundle -> {
                    log("$indent$key: $value")
                }
                else -> {
                    log("$indent$key: [Bundle]")
                    logNestedBundles(value, "$indent  ")
                }
            }
        }
    }

    private fun log(msg: String) = AppLog.i(AppLog.T.UTILS, msg)
}
