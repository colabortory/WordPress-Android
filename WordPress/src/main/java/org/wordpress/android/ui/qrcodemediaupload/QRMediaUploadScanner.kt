package org.wordpress.android.ui.qrcodemediaupload

import android.content.Context
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Reusable
import org.wordpress.android.ui.ActivityLauncher
import org.wordpress.android.util.AppLog
import javax.inject.Inject

@Reusable
class QRMediaUploadScanner @Inject constructor() {
    fun start(context: Context) {
        val scanner = GmsBarcodeScanning.getClient(context)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                AppLog.d(AppLog.T.MEDIA, "QR media upload code scanned successfully: ${barcode.rawValue}")
                barcode.rawValue?.let {
                    ActivityLauncher.startFastMediaUploadFlow(context, it)
                }
            }
            .addOnFailureListener {
                AppLog.d(AppLog.T.MEDIA, "QR media upload code scan failed: ${it.message}")
                // TODO
            }
    }
}
