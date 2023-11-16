package org.wordpress.android.ui.mysite.cards.readerstats

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.wordpress.android.ui.compose.components.card.UnelevatedCard

@Composable
fun ReaderStatsCard(
    modifier: Modifier = Modifier,
) {
    UnelevatedCard(modifier) {
        Text(text = "ReaderStatsCard")
    }
}
