package org.wordpress.android.ui.reader.views.compose

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.BalloonWindow
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.compose.setBackgroundColor
import com.skydoves.balloon.compose.setTextColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.wordpress.android.R
import org.wordpress.android.ui.compose.components.menu.dropdown.JetpackDropdownMenu
import org.wordpress.android.ui.compose.components.menu.dropdown.MenuElementData
import org.wordpress.android.ui.compose.theme.AppColor
import org.wordpress.android.ui.compose.theme.AppTheme
import org.wordpress.android.ui.compose.unit.Margin
import org.wordpress.android.ui.compose.utils.horizontalFadingEdges
import org.wordpress.android.ui.reader.viewmodels.ReaderViewModel
import org.wordpress.android.ui.reader.views.compose.filter.ReaderFilterChipGroup
import org.wordpress.android.ui.reader.views.compose.filter.ReaderFilterType
import org.wordpress.android.ui.utils.UiString

private const val ANIM_DURATION = 300
private val chipHeight = 36.dp

@Composable
fun ReaderTopAppBar(
    topBarUiState: ReaderViewModel.TopBarUiState,
    onMenuItemClick: (MenuElementData.Item.Single) -> Unit,
    onFilterClick: (ReaderFilterType) -> Unit,
    onClearFilterClick: () -> Unit,
    isSearchVisible: Boolean,
    onSearchClick: () -> Unit = {},
) {
    // TODO ideally get "isFilterTooltipNeeded" from topBarUiState and we send the info back to the ViewModel when shown
    val isFilterTooltipNeeded = true

    var selectedItem by remember { mutableStateOf(topBarUiState.selectedItem) }
    var isFilterShown by remember { mutableStateOf(topBarUiState.filterUiState != null) }
    var latestFilterState by remember { mutableStateOf(topBarUiState.filterUiState) }

    // Coordinate filter enter and exit animations with the dropdown menu (delays are required for a nice experience)
    val shouldShowFilter = topBarUiState.filterUiState != null
    LaunchedEffect(shouldShowFilter, topBarUiState.selectedItem) {
        if (isFilterShown != shouldShowFilter) {
            isFilterShown = shouldShowFilter
            if (!shouldShowFilter) delay(ANIM_DURATION.toLong())
        }
        selectedItem = topBarUiState.selectedItem
    }

    // Update filter state when it changes to non-null value. We need to keep it non-null so that the exit animation
    // works properly.
    if (topBarUiState.filterUiState != null) {
        latestFilterState = topBarUiState.filterUiState
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(scrollState)
                    .horizontalFadingEdges(scrollState, startEdgeSize = 0.dp)
                    .padding(start = Margin.ExtraLarge.value),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                JetpackDropdownMenu(
                    selectedItem = selectedItem,
                    menuItems = topBarUiState.menuItems,
                    onSingleItemClick = onMenuItemClick,
                    menuButtonHeight = chipHeight,
                    contentSizeAnimation = tween(ANIM_DURATION),
                    onDropdownMenuClick = topBarUiState.onDropdownMenuClick,
                )

                AnimatedVisibility(
                    visible = isFilterShown,
                    enter = fadeIn(tween(delayMillis = ANIM_DURATION)) +
                            slideInHorizontally(tween(delayMillis = ANIM_DURATION)) { -it / 2 },
                    exit = fadeOut(tween(ANIM_DURATION)) +
                            slideOutHorizontally(tween(ANIM_DURATION)) { -it / 2 },
                ) {
                    latestFilterState?.let { filterUiState ->
                        // use padding instead of Spacer for a nicer animation
                        Box(modifier = Modifier.padding(Margin.Medium.value)) {
                            if (!isFilterTooltipNeeded) {
                                Filter(
                                    filterUiState = filterUiState,
                                    onFilterClick = onFilterClick,
                                    onClearFilterClick = onClearFilterClick,
                                )
                            } else {
                                FilterWithTooltip(
                                    filterUiState = filterUiState,
                                    onFilterClick = onFilterClick,
                                    onClearFilterClick = onClearFilterClick,
                                    scrollState = scrollState,
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier.width(Margin.ExtraSmall.value))
        if (isSearchVisible) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { onSearchClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_magnifying_glass_16dp),
                    contentDescription = stringResource(
                        R.string.reader_search_content_description
                    ),
                    tint = MaterialTheme.colors.onSurface,
                )
            }
        }
    }
}

@Composable
private fun Filter(
    filterUiState: ReaderViewModel.TopBarUiState.FilterUiState,
    onFilterClick: (ReaderFilterType) -> Unit,
    onClearFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ReaderFilterChipGroup(
        modifier = modifier,
        selectedItem = filterUiState.selectedItem,
        blogsFilterCount = filterUiState.blogsFilterCount,
        tagsFilterCount = filterUiState.tagsFilterCount,
        showBlogsFilter = filterUiState.showBlogsFilter,
        showTagsFilter = filterUiState.showTagsFilter,
        onFilterClick = onFilterClick,
        onSelectedItemClick = { filterUiState.selectedItem?.type?.let(onFilterClick) },
        onSelectedItemDismissClick = onClearFilterClick,
        chipHeight = chipHeight,
    )
}

@Composable
private fun FilterWithTooltip(
    filterUiState: ReaderViewModel.TopBarUiState.FilterUiState,
    onFilterClick: (ReaderFilterType) -> Unit,
    onClearFilterClick: () -> Unit,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    val tooltipBackgroundColor = AppColor.DarkGray
    val tooltipTextColor = AppColor.White
    val balloonBuilder = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPosition(0.5f)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setWidth(120)
        setHeight(BalloonSizeSpec.WRAP)
        setCornerRadius(4f)
        setBalloonAnimation(BalloonAnimation.OVERSHOOT)
        setPadding(8)
        setBackgroundColor(tooltipBackgroundColor)
        setTextColor(tooltipTextColor)
        setDismissWhenTouchOutside(false)
        setDismissWhenClicked(true)
        setText("Your Tags and Blogs live here")
    }

    val coroutineScope = rememberCoroutineScope()
    var balloonWindow: BalloonWindow? by remember { mutableStateOf(null) }

    Balloon(
        builder = balloonBuilder,
        onBalloonWindowInitialized = {
            balloonWindow = it
            it.setOnBalloonDismissListener {
                coroutineScope.launch {
                    delay(ANIM_DURATION.toLong())
                    scrollState.animateScrollTo(0)
                }
            }
        },
    ) {
        Filter(
            modifier = modifier,
            filterUiState = filterUiState,
            onFilterClick = onFilterClick,
            onClearFilterClick = onClearFilterClick,
        )
    }

    LaunchedEffect(true) {
        // Scroll to the end of the filter when it's shown
        val duration = ANIM_DURATION * 2.5
        delay(duration.toLong())
        scrollState.animateScrollTo(scrollState.maxValue)
        balloonWindow?.showAlignBottom()
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReaderTopAppBarPreview() {
    val menuItems = mutableListOf<MenuElementData>(
        MenuElementData.Item.Single(
            id = "discover",
            text = UiString.UiStringRes(R.string.reader_dropdown_menu_discover),
            leadingIcon = R.drawable.ic_reader_discover_24dp,
        ),
        MenuElementData.Item.Single(
            id = "subscriptions",
            text = UiString.UiStringRes(R.string.reader_dropdown_menu_subscriptions),
            leadingIcon = R.drawable.ic_reader_subscriptions_24dp,
        ),
        MenuElementData.Item.Single(
            id = "saved",
            text = UiString.UiStringRes(R.string.reader_dropdown_menu_saved),
            leadingIcon = R.drawable.ic_reader_saved_24dp,
        ),
        MenuElementData.Item.Single(
            id = "liked",
            text = UiString.UiStringRes(R.string.reader_dropdown_menu_liked),
            leadingIcon = R.drawable.ic_reader_liked_24dp,
        ),
        MenuElementData.Item.SubMenu(
            id = "subMenu1",
            text = UiString.UiStringText("Funny Blogs"),
            children = listOf(
                MenuElementData.Item.Single(
                    id = "funnyBlog1",
                    text = UiString.UiStringText("Funny Blog 1"),
                ),
                MenuElementData.Item.Single(
                    id = "funnyBlog2",
                    text = UiString.UiStringText("Funny Blog 2"),
                ),
            ),
        )
    )

    var topBarUiState by remember {
        mutableStateOf(
            ReaderViewModel.TopBarUiState(
                menuItems = menuItems,
                selectedItem = menuItems.first() as MenuElementData.Item.Single,
                onDropdownMenuClick = {},
            )
        )
    }

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            ReaderTopAppBar(
                topBarUiState = topBarUiState,
                onMenuItemClick = {
                    topBarUiState = topBarUiState.copy(
                        selectedItem = it
                    )
                },
                onFilterClick = {},
                onClearFilterClick = {},
                isSearchVisible = true,
                onSearchClick = {},
            )
        }
    }
}
