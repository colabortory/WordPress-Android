package org.wordpress.android.ui.mysite.cards.readerstats

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.wordpress.android.R
import org.wordpress.android.ui.compose.components.card.UnelevatedCard
import org.wordpress.android.ui.compose.styles.DashboardCardTypography
import org.wordpress.android.ui.compose.utils.uiStringText
import org.wordpress.android.ui.utils.UiString
import org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.ReaderStatsCard as ReaderStatsCardModel

private val labelColor = Color(0x993C3C43)
private val avatarSize = 64.dp

@Composable
fun ReaderStatsCard(
    model: ReaderStatsCardModel,
    modifier: Modifier = Modifier,
) {
    UnelevatedCard(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            WeeklyStatSection(
                readTime = model.thisWeekTime,
                achievement = model.thisWeekAchievement,
            )

            MostReadSitesSection(
                model.mostReadSites,
                onSiteClick = model.onSiteClick
            )

            Text(
                text = "Share with friends",
                style = DashboardCardTypography.footerCTA,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable(onClick = model.onShareClick)
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
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = CircleShape,
                )
                .padding(4.dp)
        )

        Text(
            uiStringText(readTime),
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
            )
        )

        Text(
            "Reading time this week",
            style = MaterialTheme.typography.titleMedium,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentWidth(),
        ) {
            achievement.iconRes?.let { iconRes ->
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = labelColor,
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = uiStringText(achievement.text),
                fontWeight = FontWeight.Normal,
                color = labelColor,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
private fun MostReadSitesSection(
    mostReadSites: List<ReaderStatsCardModel.MostReadSite>,
    onSiteClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Your favourite blogs",
            color = labelColor,
            style = MaterialTheme.typography.bodySmall,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            mostReadSites.take(3).forEach { mostReadSite ->
                MostReadSiteItem(
                    mostReadSite,
                    onClick = { onSiteClick(mostReadSite.blogId) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun MostReadSiteItem(
    mostReadSite: ReaderStatsCardModel.MostReadSite,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            model = mostReadSite.blogAvatarUrl,
            placeholder = ColorPainter(colorResource(R.color.placeholder)),
            error = ColorPainter(colorResource(R.color.placeholder)),
            fallback = ColorPainter(colorResource(R.color.placeholder)),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(avatarSize)
                .clip(CircleShape),
        )

        Text(
            text = uiStringText(mostReadSite.blogName),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
        )
    }
}
