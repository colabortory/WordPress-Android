package org.wordpress.android.ui.mediauploads.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.wordpress.android.R
import org.wordpress.android.ui.compose.components.MainTopAppBar
import org.wordpress.android.ui.compose.components.NavigationIcons

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
                title = stringResource(id = R.string.new_domain_search_screen_title),
                navigationIcon = NavigationIcons.CloseIcon,
                onNavigationIconClick = onCloseTapped,
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Button(onClick = onPickMediaTapped) {
                    Text(text = "Pick media")
                }
            }
        }
    )
}
