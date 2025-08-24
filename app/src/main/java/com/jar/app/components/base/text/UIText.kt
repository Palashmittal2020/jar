package com.jar.app.components.base.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun UITextHeadingH3(
    text: String,
    color: Color,
) {
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.sp,
        color = color
    )
}

@Composable
fun UITextHeadingH4(
    text: String,
    color: Color,
) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp,
        color = color
    )
}

@Composable
fun UITextHeadingH5(
    text: String,
    color: Color,
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        color = color
    )
}

@Composable
fun UITextHeadingH6(
    text: String,
    color: Color,
) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp,
        color = color
    )
}
