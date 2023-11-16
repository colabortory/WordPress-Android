package org.wordpress.android.ui.mysite.cards.readerstats

import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import org.wordpress.android.databinding.ReaderStatsCardBinding
import org.wordpress.android.ui.compose.theme.AppTheme
import org.wordpress.android.ui.mysite.MySiteCardAndItem
import org.wordpress.android.ui.mysite.MySiteCardAndItemViewHolder
import org.wordpress.android.util.extensions.viewBinding

class ReaderStatsCardViewHolder(parent: ViewGroup) :
    MySiteCardAndItemViewHolder<ReaderStatsCardBinding>(parent.viewBinding(ReaderStatsCardBinding::inflate)) {
    fun bind(cardModel: MySiteCardAndItem.Card.ReaderStatsCard) = with(binding) {
        readerStatsCard.setContent {
            AppTheme {
                ReaderStatsCard(
                    model = cardModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}
