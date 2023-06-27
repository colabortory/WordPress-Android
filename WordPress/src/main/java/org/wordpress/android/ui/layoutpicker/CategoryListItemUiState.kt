package org.wordpress.android.ui.layoutpicker

import org.wordpress.android.R

/**
 * The category list item
 */
data class CategoryListItemUiState(
    val slug: String,
    val title: String,
    val emoji: String,
    val selected: Boolean,
    val onItemTapped: (() -> Unit)
) {
    val background: Int
        get() = if (selected) R.attr.categoriesButtonBackgroundSelected else R.attr.categoriesButtonBackground

    val textColor: Int
        get() = if (selected) {
            com.google.android.material.R.attr.colorOnPrimary
        } else {
            com.google.android.material.R.attr.colorOnSurface
        }

    val checkIconVisible: Boolean
        get() = selected

    val emojiIconVisible: Boolean
        get() = !selected

    val contentDescriptionResId: Int
        get() = if (selected) R.string.mlp_selected_description else R.string.mlp_notselected_description
}
