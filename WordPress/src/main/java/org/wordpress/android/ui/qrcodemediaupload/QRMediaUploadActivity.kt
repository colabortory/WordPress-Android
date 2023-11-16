package org.wordpress.android.ui.qrcodemediaupload

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.android.ui.ActivityLauncher
import org.wordpress.android.ui.LocaleAwareActivity
import org.wordpress.android.ui.domains.management.M3Theme

@AndroidEntryPoint
class QRMediaUploadActivity : LocaleAwareActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            M3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            // context.startActivity(newIntent(context)) // TODO
            ActivityLauncher.startFastMediaUploadFlow(context, "")
        }

        @JvmStatic
        fun newIntent(context: Context, uri: String? = null, fromDeeplink: Boolean = false): Intent {
            val intent = Intent(context, QRMediaUploadActivity::class.java).apply {
                putExtra(DEEP_LINK_URI_KEY, uri)
                putExtra(IS_DEEP_LINK_KEY, fromDeeplink)
            }
            return intent
        }

        private const val IS_DEEP_LINK_KEY = "is_deep_link_key"
        private const val DEEP_LINK_URI_KEY = "deep_link_uri_key"
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    M3Theme {
        Greeting("Android")
    }
}
