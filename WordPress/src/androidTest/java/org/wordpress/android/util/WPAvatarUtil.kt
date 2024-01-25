package org.wordpress.android.util

import com.gravatar.DefaultAvatarImage
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

import junit.framework.TestCase.assertEquals

@HiltAndroidTest
class WPAvatarUtil {
    @Test
    fun rewriteAvatarUrlReplaceNonGravatarUrlToPhotonUrl() {
        assertEquals("https://i0.wp.com/example.com/image.jpg?strip=info&quality=65&resize=100,100&ssl=1",
            WPAvatarUtils.rewriteAvatarUrl("https://example.com/image.jpg", 100)
        )
    }

    @Test
    fun rewriteAvatarUrlDropQueryParamsFromGravatarUrlAndAddDefaults() {
        assertEquals("https://www.gravatar.com/avatar/" +
                "31c5543c1734d25c7206f5fd591525d0295bec6fe84ff82f946a34fe970a1e66?d=mp&s=100",
            WPAvatarUtils.rewriteAvatarUrl(
            "https://www.gravatar.com/avatar/" +
                "31c5543c1734d25c7206f5fd591525d0295bec6fe84ff82f946a34fe970a1e66?d=wavatar&s=1000",
            100)
        )
    }

    @Test
    fun rewriteAvatarUrlAddDefaultsToGravatarUrl() {
        assertEquals("https://www.gravatar.com/avatar/" +
                "31c5543c1734d25c7206f5fd591525d0295bec6fe84ff82f946a34fe970a1e66?d=404&s=200",
            WPAvatarUtils.rewriteAvatarUrl(
                "https://www.gravatar.com/avatar/" +
                        "31c5543c1734d25c7206f5fd591525d0295bec6fe84ff82f946a34fe970a1e66",
                200, DefaultAvatarImage.STATUS_404)
        )
    }
}
