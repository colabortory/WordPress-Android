package org.wordpress.android.ui.qrcodemediaupload

import android.content.Context
import android.net.UrlQuerySanitizer
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Reusable
import org.json.JSONObject
import org.wordpress.android.ui.ActivityLauncher
import org.wordpress.android.util.AppLog
import org.wordpress.android.util.UriWrapper
import javax.inject.Inject

@Reusable
class QRMediaUploadScanner @Inject constructor() {
    fun start(context: Context) {
        val scanner = GmsBarcodeScanning.getClient(context)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                AppLog.d(AppLog.T.MEDIA, "QR media upload code scanned successfully: ${barcode.rawValue}")
                process(barcode.rawValue) { siteId, postId ->
                    ActivityLauncher.startFastMediaUploadFlow(context, siteId, postId)
                }
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    @Suppress("ReturnCount")
    fun process(
        scannedUrl: String?,
        success: (siteId: String, postId: String) -> Unit = { _, _ -> }
    ): Pair<String, String> {
        val params = extractQueryParams(scannedUrl)
        if (!isValid(params)) {
            onError(Exception("Invalid QR code"))
            return Pair("", "") // TODO handle error
        }
        val dataParams = extractDataJsonParameters(params[DATA_KEY])
        val siteId = dataParams[SITE_ID_KEY]
        val postId = dataParams[POST_ID_KEY]
        if (siteId.isNullOrEmpty() || postId.isNullOrEmpty()) {
            onError(Exception("Invalid QR code"))
            return Pair("", "") // TODO handle error
        }
        success(siteId, postId)
        return Pair(siteId, postId)
    }

    private fun onError(exception: Exception) {
        AppLog.d(AppLog.T.MEDIA, "QR media upload code scan failed: ${exception.message}")
        // TODO
    }

    // https://apps.wordpress.com/get?campaign=qr-code-media&data={post_id:POST_ID,site_id:WPCOM_BLOG_ID}
    private fun extractQueryParams(scannedValue: String?): Map<String, String> {
        if (scannedValue.isNullOrEmpty()) return emptyMap()
        val uri = UriWrapper(scannedValue)
        val queryParams = mutableMapOf<String, String>()
        uri.fragment?.let {
            UrlQuerySanitizer(it).parameterList.forEach { pair ->
                queryParams[pair.mParameter] = pair.mValue
            }
        }
        return queryParams
    }

    private fun isValid(scannedParas: Map<String, String>): Boolean {
        if (scannedParas[CAMPAIGN_KEY] != CAMPAIGN_VALUE || scannedParas[DATA_KEY].isNullOrEmpty()) return false
        return true
    }

    private fun extractDataJsonParameters(data: String?): Map<String, String?> {
        if (data.isNullOrEmpty()) return emptyMap()
        val dataJson = JSONObject(data)
        val postId = dataJson.optString(POST_ID_KEY)
        val siteId = dataJson.optString(SITE_ID_KEY)
        return mapOf(POST_ID_KEY to postId, SITE_ID_KEY to siteId)
    }

    companion object {
        const val CAMPAIGN_KEY = "campaign"
        const val DATA_KEY = "data"
        const val POST_ID_KEY = "post_id"
        const val SITE_ID_KEY = "site_id"
        const val CAMPAIGN_VALUE = "qr-code-media"
    }
}
