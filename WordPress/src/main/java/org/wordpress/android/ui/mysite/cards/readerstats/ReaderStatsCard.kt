package org.wordpress.android.ui.mysite.cards.readerstats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.wordpress.android.R
import org.wordpress.android.ui.compose.components.ContentAlphaProvider
import org.wordpress.android.ui.compose.components.card.UnelevatedCard
import org.wordpress.android.ui.compose.utils.uiStringText
import org.wordpress.android.ui.utils.UiString
import org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.ReaderStatsCard as ReaderStatsCardModel

@Composable
fun ReaderStatsCard(
    model: ReaderStatsCardModel,
    modifier: Modifier = Modifier,
) {
    UnelevatedCard(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            WeeklyStatSection(
                readTime = model.thisWeekTime,
                achievement = model.thisWeekAchievement,
            )
        }
    }
}

@Composable
private fun WeeklyStatSection(
    readTime: UiString,
    achievement: ReaderStatsCardModel.Achievement,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_bookmark_outline_new_24dp),
            contentDescription = null,
            tint = MaterialTheme.colors.surface,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.onSurface,
                    shape = CircleShape,
                )
                .padding(4.dp)
        )

        Text(
            uiStringText(readTime),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5
        )

        Text(
            "Reading time this week",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.wrapContentWidth(),
        ) {
            ContentAlphaProvider(alpha = 0.4f) {
                achievement.iconRes?.let { iconRes ->
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = MaterialTheme.colors.surface,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Text(
                    text = uiStringText(achievement.text),
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}
