package com.jar.app.components.onBoarding

import android.graphics.Bitmap
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jar.app.R

@Composable
fun OnBoardingInfoCardLayout(
    isSelected: Boolean,
    imageBitMap: Bitmap?,
    collapsedStateText: String,
    expandedStateText: String,
    collapseExpandIntroInterval: Int,
    onSelectionChange: () -> Unit,
) {

    val size by animateFloatAsState(
        targetValue = if (isSelected) 1F else .1F, label = "",
        animationSpec = tween(durationMillis = collapseExpandIntroInterval),
    )

    val offSet by animateDpAsState(
        targetValue = if (isSelected) 0.dp else (-30).dp, label = "",
        animationSpec = tween(durationMillis = collapseExpandIntroInterval),
    )

    val offSetRound by animateDpAsState(
        targetValue = if (isSelected) 28.dp else (100).dp, label = "",
        animationSpec = tween(durationMillis = collapseExpandIntroInterval),
    )

    val fontSize by animateIntAsState(
        targetValue = if (isSelected) 20 else 16, label = "",
    )

    val lineHeight by animateIntAsState(
        targetValue = if (isSelected) 20 else 28, label = "",
        animationSpec = tween(durationMillis = collapseExpandIntroInterval),
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isSelected) 0f else 1f,
        animationSpec = tween(durationMillis = collapseExpandIntroInterval),
        label = "alphaAnim"
    )

    Column(
        modifier = Modifier
            .clickable { onSelectionChange() }
            .clip(shape = RoundedCornerShape(28.dp))
            .background(
                color = Color(0xFF28085C).copy(alpha = 0.32f)
            )
            .border(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF28085C).copy(alpha = 0.32f),
                        Color(0xFFFFFFFF).copy(.3f)
                    )
                ),
                width = 1.dp,
                shape = RoundedCornerShape(28.dp)
            )
            .padding(16.dp))
    {
        if (imageBitMap != null) {
            Image(
                bitmap = imageBitMap.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(offSetRound))
                    .fillMaxWidth(size)
                    .aspectRatio(.86f),
                contentScale = ContentScale.FillBounds,
            )
        } else {
            Image(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(offSetRound))
                    .fillMaxWidth(size)
                    .aspectRatio(.86f),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(
                    top = if (isSelected) 16.dp else 0.dp,
                    start = 16.dp
                )
                .offset(y = offSet),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = if (isSelected) expandedStateText else collapsedStateText,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = lineHeight.sp,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = "",
                modifier = Modifier.alpha(animatedAlpha)
            )
        }
    }
}