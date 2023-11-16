package org.wordpress.android.ui.mediauploads.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.wordpress.android.R
import org.wordpress.android.ui.compose.components.MainTopAppBar
import org.wordpress.android.ui.compose.components.NavigationIcons
import org.wordpress.android.ui.domains.management.M3Theme
import org.wordpress.android.ui.domains.management.composable.PrimaryButton

@Composable
fun FastMediaUploadScreen(
    modifier: Modifier = Modifier,
    onPickMediaTapped: () -> Unit,
    onCloseTapped: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                title = null,
                navigationIcon = NavigationIcons.CloseIcon,
                onNavigationIconClick = onCloseTapped,
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.fast_media_upload_screen_title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(4.dp)
                )
                Text(
                    text = stringResource(id = R.string.fast_media_upload_screen_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(4.dp)
                )
                PrimaryButton(
                    onClick = onPickMediaTapped,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 20.dp),
                    text = stringResource(id = R.string.fast_media_upload_screen_upload_button),
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}

@Preview
@Composable
fun FastMediaUploadScreenPreview() {
    M3Theme {
        FastMediaUploadScreen(onPickMediaTapped = {}, onCloseTapped = {})
    }
}
