package org.wordpress.android.ui.posts.prepublishing.home

import androidx.annotation.ColorRes
import org.wordpress.android.R
import org.wordpress.android.ui.utils.UiString

typealias PublishPost = Boolean

sealed class PrepublishingHomeItemUiState {
    data class HomeUiState(
        val actionType: ActionType,
        @ColorRes val actionTypeColor: Int = R.color.prepublishing_action_type_enabled_color,
        val actionResult: UiString? = null,
        @ColorRes val actionResultColor: Int = R.color.prepublishing_action_result_enabled_color,
        val actionClickable: Boolean,
        val onActionClicked: ((actionType: ActionType) -> Unit)?
    ) : PrepublishingHomeItemUiState()

    data class StoryTitleUiState(
        val storyThumbnailUrl: String,
        val storyTitle: UiString.UiStringText? = null,
        val onStoryTitleChanged: (String) -> Unit
    ) :
        PrepublishingHomeItemUiState()

    data class HeaderUiState(val siteName: UiString.UiStringText, val siteIconUrl: String) :
        PrepublishingHomeItemUiState()

    sealed class ButtonUiState(
        val buttonText: UiString.UiStringRes,
        val publishPost: PublishPost
    ) : PrepublishingHomeItemUiState() {
        open val onButtonClicked: ((PublishPost) -> Unit)? = null

        data class PublishButtonUiState(override val onButtonClicked: (PublishPost) -> Unit) : ButtonUiState(
            UiString.UiStringRes(R.string.prepublishing_nudges_home_publish_button),
            true
        )

        data class ScheduleButtonUiState(override val onButtonClicked: (PublishPost) -> Unit) : ButtonUiState(
            UiString.UiStringRes(R.string.prepublishing_nudges_home_schedule_button),
            false
        )

        data class UpdateButtonUiState(override val onButtonClicked: (PublishPost) -> Unit) : ButtonUiState(
            UiString.UiStringRes(R.string.prepublishing_nudges_home_update_button),
            false
        )

        data class SubmitButtonUiState(override val onButtonClicked: (PublishPost) -> Unit) : ButtonUiState(
            UiString.UiStringRes(R.string.prepublishing_nudges_home_submit_button),
            true
        )

        data class SaveButtonUiState(override val onButtonClicked: (PublishPost) -> Unit) : ButtonUiState(
            UiString.UiStringRes(R.string.prepublishing_nudges_home_save_button),
            false
        )
    }

    enum class ActionType(val textRes: UiString.UiStringRes) {
        PUBLISH(UiString.UiStringRes(R.string.prepublishing_nudges_publish_action)),
        TAGS(UiString.UiStringRes(R.string.prepublishing_nudges_tags_action)),
        CATEGORIES(UiString.UiStringRes(R.string.prepublishing_nudges_categories_action)),
        ADD_CATEGORY(UiString.UiStringRes(R.string.prepublishing_nudges_categories_action))
    }
}