package com.jar.app.components.base.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jar.app.R
import com.jar.app.components.base.text.UITextHeadingH5
import kotlinx.coroutines.delay

@Composable
fun ScreenHeaderLayout(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_back_btn),
            contentDescription = "",
        )

        Spacer(modifier = Modifier.width(16.dp))

        UITextHeadingH5(
            text = title,
            color = Color.White,
        )
    }
}

@Composable
fun AnimatedScreenHeader(title: String) {
    var isHeaderVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(100L)
        isHeaderVisible = true
    }
    AnimatedVisibility(
        visible = isHeaderVisible
    ) {
        ScreenHeaderLayout(
            title = title
        )
    }
}