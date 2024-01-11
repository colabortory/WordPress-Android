package org.wordpress.android.ui.mysite.cards.dynamiccard

import org.wordpress.android.fluxc.model.dashboard.CardModel.DynamicCardsModel.CardOrder
import org.wordpress.android.fluxc.model.dashboard.CardModel.DynamicCardsModel.DynamicCardModel
import org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandlers
import org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.Dynamic
import org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.DynamicCardsBuilderParams
import org.wordpress.android.ui.utils.ListItemInteraction
import org.wordpress.android.util.UrlUtilsWrapper
import org.wordpress.android.util.config.DynamicDashboardCardsFeatureConfig
import javax.inject.Inject

class DynamicCardsBuilder @Inject constructor(
    private val urlUtils: UrlUtilsWrapper,
    private val deepLinkHandlers: DeepLinkHandlers,
    private val dynamicDashboardCardsFeatureConfig: DynamicDashboardCardsFeatureConfig,
) {
    fun build(params: DynamicCardsBuilderParams, order: CardOrder): List<Dynamic>? {
        if (!dynamicDashboardCardsFeatureConfig.isEnabled() || !shouldBuildCard(params, order)) {
            return null
        }
        return convertToDynamicCards(params, order)
    }

    private fun shouldBuildCard(params: DynamicCardsBuilderParams, order: CardOrder): Boolean {
        return !(params.dynamicCards == null || params.dynamicCards.dynamicCards.none { it.order == order })
    }

    private fun convertToDynamicCards(params: DynamicCardsBuilderParams, order: CardOrder): List<Dynamic> {
        val cards = params.dynamicCards?.dynamicCards?.filter { it.order == order }.orEmpty()
        return cards.map { card ->
            Dynamic(
                id = card.id,
                rows = card.rows.map { row ->
                    Dynamic.Row(
                        iconUrl = row.icon,
                        title = row.title,
                        description = row.description,
                    )
                },
                title = card.title,
                image = card.featuredImage,
                action = getActionSource(params, card),
                onHideMenuItemClick = ListItemInteraction.create(card.id, params.onHideMenuItemClick),
            )
        }
    }

    private fun getActionSource(
        params: DynamicCardsBuilderParams,
        card: DynamicCardModel
    ): Dynamic.ActionSource? = when {
        isValidUrlOrDeeplink(card.url) && isValidActionTitle(card.action) -> Dynamic.ActionSource.CardOrButton(
            url = requireNotNull(card.url),
            title = requireNotNull(card.action),
            onCardClick = ListItemInteraction.create(
                data = DynamicCardsBuilderParams.ClickParams(id = card.id, actionUrl = requireNotNull(card.url)),
                action = params.onCardClick
            ),
            onButtonClick = ListItemInteraction.create(
                data = DynamicCardsBuilderParams.ClickParams(id = card.id, actionUrl = requireNotNull(card.url)),
                action = params.onCtaClick
            )
        )
        isValidUrlOrDeeplink(card.url) -> Dynamic.ActionSource.Card(
            url = requireNotNull(card.url),
            onCardClick = ListItemInteraction.create(
                data = DynamicCardsBuilderParams.ClickParams(id = card.id, actionUrl = requireNotNull(card.url)),
                action = params.onCardClick
            )
        )
        else -> null
    }

    private fun isValidUrlOrDeeplink(url: String?): Boolean {
        return !url.isNullOrEmpty() && (urlUtils.isValidUrlAndHostNotNull(url)
                || deepLinkHandlers.isDeepLink(url))
    }

    private fun isValidActionTitle(title: String?): Boolean {
        return !title.isNullOrEmpty()
    }
}
