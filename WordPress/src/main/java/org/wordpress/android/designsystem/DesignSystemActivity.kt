package org.wordpress.android.designsystem

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.wordpress.android.ui.LocaleAwareActivity
import org.wordpress.android.util.extensions.setContent

class DesignSystemActivity : LocaleAwareActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesignSystem()
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    
    @Composable
    fun PreviewDesignSystemActivity() {
        DesignSystem()
    }
}


