package com.jar.app.presentation.onBoarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jar.app.R
import com.jar.app.components.base.header.AnimatedScreenHeader
import com.jar.app.components.base.text.UITextHeadingH3
import com.jar.app.components.base.text.UITextHeadingH4
import com.jar.app.components.base.text.UITextHeadingH6
import com.jar.app.components.onBoarding.OnBoardingInfoCardLayout
import com.jar.app.domain.models.ManualBuyEducationDataUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@Composable
fun OnBoardingScreen(
    onBoardingDataFlow: StateFlow<ManualBuyEducationDataUiModel?>,
    selectedCardIndexFLow: StateFlow<Int>,
    onSelectedCardIndexChange: (Int) -> Unit,
) {
    val onBoardingData by onBoardingDataFlow.collectAsState()
    val selectedCardIndex by selectedCardIndexFLow.collectAsState()
    var onBoardingCardVisible by remember { mutableStateOf(false) }
    var isLandingPageVisible by remember { mutableStateOf(false) }

    onBoardingData?.let { onBoardingData ->

        LaunchedEffect(key1 = Unit) {
            delay(2000L)
            onBoardingCardVisible = true
        }

        AnimatedVisibility(
            visible = !onBoardingCardVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 200)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF201929)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    UITextHeadingH3(
                        text = onBoardingData.introTitle,
                        color = Color.White
                    )

                    UITextHeadingH4(
                        text = onBoardingData.introSubtitle,
                        color = Color(0xFFF8DC83)
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = isLandingPageVisible,
                enter = slideInVertically(
                    animationSpec = tween(1000),
                    initialOffsetY = { it / 4 }
                ) + fadeIn(tween(1000))
            ) {
                LandingPageScreen()
            }

            AnimatedVisibility(
                visible = onBoardingCardVisible && !isLandingPageVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                exit = slideOutVertically(
                    animationSpec = tween(durationMillis = 1000),
                    targetOffsetY = { -it }
                ) + fadeOut(tween(1000))
            ) {
                val intervalMs =
                    onBoardingData.expandCardStayInterval.toInt().coerceAtLeast(1500)

                val introPlayed = rememberSaveable { mutableStateOf(false) }
                val visible = remember { mutableStateListOf(false, false, false) }

                var isCTAVisible by remember { mutableStateOf(false) }

                LaunchedEffect(key1 = Unit) {
                    delay(intervalMs.toLong() * (onBoardingData.educationCardList.size + 1))
                    isCTAVisible = true
                }

                LaunchedEffect(Unit) {
                    if (!introPlayed.value) {
                        for (i in 0..2) {
                            visible[i] = true
                            delay(onBoardingData.bottomToCenterTranslationInterval)
                        }

                        onSelectedCardIndexChange(0)
                        delay(intervalMs.toLong())

                        onSelectedCardIndexChange(1)
                        delay(intervalMs.toLong())

                        onSelectedCardIndexChange(2)
                        introPlayed.value = true
                    }
                }

                val firstColor by animateColorAsState(
                    targetValue = when (selectedCardIndex) {
                        0 -> {
                            val color =
                                onBoardingData.educationCardList[0].backGroundColor.toColor()
                            color.copy(alpha = .16f)
                        }

                        -1 -> {
                            val color =
                                onBoardingData.educationCardList[0].backGroundColor.toColor()
                            color.copy(alpha = .16f)
                        }

                        1 -> {
                            val color =
                                onBoardingData.educationCardList[1].backGroundColor.toColor()
                            color.copy(alpha = .16f)
                        }

                        else -> {
                            val color =
                                onBoardingData.educationCardList[2].backGroundColor.toColor()
                            color.copy(alpha = .2f)
                        }
                    }
                )

                val secondColor by animateColorAsState(
                    targetValue = when (selectedCardIndex) {
                        0 -> {
                            val color =
                                onBoardingData.educationCardList[0].backGroundColor.toColor()
                            color.copy(alpha = .8f)
                        }

                        -1 -> {
                            val color =
                                onBoardingData.educationCardList[0].backGroundColor.toColor()
                            color.copy(alpha = .8f)
                        }

                        1 -> {
                            val color =
                                onBoardingData.educationCardList[1].backGroundColor.toColor()
                            color
                        }

                        else -> {
                            val color =
                                onBoardingData.educationCardList[2].backGroundColor.toColor()
                            color
                        }
                    }
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        firstColor, secondColor
                                    )
                                )
                            ),
                    ) {
                        AnimatedScreenHeader(title = stringResource(R.string.header_onBoarding))

                        Spacer(modifier = Modifier.height(height = 12.dp))

                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {

                            CardItem(
                                visible = visible[0],
                                index = 0,
                                baseDelayMs = intervalMs,
                                content = {
                                    OnBoardingInfoCardLayout(
                                        isSelected = selectedCardIndex == 0,
                                        imageBitMap = onBoardingData.educationCardList[0].image,
                                        expandedStateText = onBoardingData.educationCardList[0].expandStateText,
                                        collapsedStateText = onBoardingData.educationCardList[0].collapsedStateText,
                                        collapseExpandIntroInterval = onBoardingData.collapseExpandIntroInterval.toInt(),
                                        onSelectionChange = {
                                            onSelectedCardIndexChange(0)
                                        })
                                }
                            )


                            CardItem(
                                visible = visible[0],
                                index = 1,
                                baseDelayMs = intervalMs,
                                content = {
                                    OnBoardingInfoCardLayout(
                                        isSelected = selectedCardIndex == 1,
                                        imageBitMap = onBoardingData.educationCardList[1].image,
                                        expandedStateText = onBoardingData.educationCardList[1].expandStateText,
                                        collapsedStateText = onBoardingData.educationCardList[1].collapsedStateText,
                                        collapseExpandIntroInterval = onBoardingData.collapseExpandIntroInterval.toInt(),
                                        onSelectionChange = {
                                            onSelectedCardIndexChange(1)
                                        })
                                }
                            )

                            CardItem(
                                visible = visible[0],
                                index = 2,
                                baseDelayMs = intervalMs,
                                content = {
                                    OnBoardingInfoCardLayout(
                                        isSelected = selectedCardIndex == 2,
                                        imageBitMap = onBoardingData.educationCardList[2].image,
                                        expandedStateText = onBoardingData.educationCardList[2].expandStateText,
                                        collapsedStateText = onBoardingData.educationCardList[2].collapsedStateText,
                                        collapseExpandIntroInterval = onBoardingData.collapseExpandIntroInterval.toInt(),
                                        onSelectionChange = {
                                            onSelectedCardIndexChange(2)
                                        })
                                }
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = isCTAVisible,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(bottom = 24.dp)
                                .background(
                                    color = onBoardingData.saveButtonCta.backgroundColor.toColor(),
                                    shape = RoundedCornerShape(size = 31.dp)
                                )
                                .padding(vertical = 6.dp, horizontal = 20.dp)
                                .clickable {
                                    isLandingPageVisible = true
                                },
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            UITextHeadingH6(
                                text = onBoardingData.saveButtonCta.text,
                                color = onBoardingData.saveButtonCta.textColor.toColor()
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            val composition by rememberLottieComposition(
                                LottieCompositionSpec.RawRes(R.raw.bottom_cta_animation)
                            )

                            val progress by animateLottieCompositionAsState(
                                composition,
                                iterations = LottieConstants.IterateForever
                            )

                            LottieAnimation(
                                composition = composition,
                                progress = progress,
                                modifier = Modifier
                                    .size(44.dp)
                                    .rotate(180f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CardItem(
    visible: Boolean,
    index: Int,
    baseDelayMs: Int,
    content: @Composable () -> Unit
) {
    val cfg = LocalConfiguration.current
    val density = LocalDensity.current
    val screenHeightPx = with(density) { cfg.screenHeightDp.dp.roundToPx() }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { screenHeightPx },
            animationSpec = tween(
                durationMillis = 1500,
                delayMillis = index * baseDelayMs,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(tween(1500)),
        exit = fadeOut()
    ) {
        val appearAlpha by animateFloatAsState(if (visible) 1f else 0f, label = "alpha")
        val appearOffset by animateDpAsState(if (visible) 0.dp else 12.dp, label = "offset")

        Box(
            Modifier
                .alpha(appearAlpha)
                .offset(y = appearOffset)
        ) {
            content()
        }
    }
}

private fun String.toColor(): Color {
    val cleanHex = removePrefix("#")
    val colorLong = ("FF$cleanHex").toLong(16)
    return Color(colorLong)
}