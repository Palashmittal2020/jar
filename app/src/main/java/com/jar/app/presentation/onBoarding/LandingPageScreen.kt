package com.jar.app.presentation.onBoarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jar.app.components.base.header.ScreenHeaderLayout
import com.jar.app.components.base.text.UITextHeadingH3

@Composable
fun LandingPageScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF201929))
    ) {
        ScreenHeaderLayout(
            title = "Landing Page"
        )

        HorizontalDivider()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UITextHeadingH3(
                text = "Landing page",
                color = Color.White
            )
        }
    }
}