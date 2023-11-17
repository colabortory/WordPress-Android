package org.wordpress.android.ui.deeplinks.handlers

import org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction
import org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenQRMediaUploadFlow
import org.wordpress.android.ui.qrcodemediaupload.QRMediaUploadScanner
import org.wordpress.android.util.UriWrapper
import javax.inject.Inject

class QRCodeMediaUploadLinkHandler @Inject constructor() : DeepLinkHandler {
    @Inject
    lateinit var qrMediaUploadScanner: QRMediaUploadScanner

    /**
     * Returns true if the URI looks like `TODO`
     */
    override fun shouldHandleUrl(uri: UriWrapper): Boolean {
        // https://apps.wordpress.com/get?campaign=qr-code-media&data={post_id:POST_ID,site_id:WPCOM_BLOG_ID}
        return uri.host == HOST_APPS_WORDPRESS_COM &&
                uri.pathSegments.firstOrNull() == GET_PATH
    }

    override fun buildNavigateAction(uri: UriWrapper): NavigateAction {
        val result = qrMediaUploadScanner.process(uri.toString())
        val siteId = result.first
        val postId = result.second
        return OpenQRMediaUploadFlow(siteId, postId)
    }

    override fun stripUrl(uri: UriWrapper): String {
        return buildString {
            append("$HOST_APPS_WORDPRESS_COM/$GET_PATH")
        }
    }

    companion object {
        private const val GET_PATH = "get"
        private const val HOST_APPS_WORDPRESS_COM = "apps.wordpress.com"
    }
}
