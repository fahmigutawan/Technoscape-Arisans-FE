package com.fahgutawan.arisans.util

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.tahutelor.arisans.ui.theme.GrayMid
import com.tahutelor.arisans.ui.theme.White

@Composable
fun MyTopBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val height = LocalConfiguration.current.screenHeightDp
    val scaledheight = height / 10

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(scaledheight.dp), elevation = 10.dp,
        color = White
    ) {
        Box(modifier = modifier.padding(all = 16.dp), content = { content() })
    }
}