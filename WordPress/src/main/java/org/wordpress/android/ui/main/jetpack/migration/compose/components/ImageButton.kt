package org.wordpress.android.ui.main.jetpack.migration.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import org.wordpress.android.R
import org.wordpress.android.ui.compose.unit.FontSize
import org.wordpress.android.ui.utils.UiString

class Drawable(val resId: Int, val iconSize: Dp = 24.dp)


@Preview
@Composable
fun PreviewDrawButton() {
    ImageButton(
        drawableLeft = Drawable(R.drawable.ic_story_icon_24dp),
        buttonText = UiString.UiStringText("Button Text"),
        onClick = {}
    )
}


@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    drawableLeft: Drawable? = null,
    drawableRight: Drawable? = null,
    drawableTop: Drawable? = null,
    drawableBottom: Drawable? = null,
    buttonText: UiString,
    onClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier) {
        val (buttonTextRef) = createRefs()
        Box(modifier = Modifier
            .constrainAs(buttonTextRef) {
                top.linkTo(parent.top, drawableTop?.iconSize ?: 0.dp)
                bottom.linkTo(parent.bottom, drawableBottom?.iconSize ?: 0.dp)
                start.linkTo(parent.start, drawableLeft?.iconSize ?: 0.dp)
                end.linkTo(parent.end, drawableRight?.iconSize ?: 0.dp)
            }
            .clickable { onClick.invoke() }
        ) {
            val buttonTextValue = when (buttonText) {
                is UiString.UiStringText -> buttonText.text
                is UiString.UiStringRes -> stringResource(id = buttonText.stringRes)
                is UiString.UiStringResWithParams -> stringResource(id = buttonText.stringRes)
                else -> ""
            }

            Text(
                text = buttonTextValue.toString(),
                fontSize = FontSize.Large.value,
                fontWeight = FontWeight.Light,
                color = Color.LightGray
            )
        }

        drawableLeft?.let { drawable ->
            val (imageLeft) = createRefs()
            Image(
                modifier = Modifier.constrainAs(imageLeft) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                painter = painterResource(id = drawable.resId),
                contentDescription = null
            )
        }

        drawableRight?.let { drawable ->
            val (imageRight) = createRefs()
            Image(
                modifier = Modifier.constrainAs(imageRight) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
                painter = painterResource(id = drawable.resId),
                contentDescription = null
            )
        }

        drawableTop?.let { drawable ->
            val (imageTop) = createRefs()
            Image(
                modifier = Modifier.constrainAs(imageTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                painter = painterResource(id = drawable.resId),
                contentDescription = null
            )
        }

        drawableBottom?.let { drawable ->
            val (imageBottom) = createRefs()
            Image(
                modifier = Modifier.constrainAs(imageBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                painter = painterResource(id = drawable.resId),
                contentDescription = null
            )
        }
    }
}