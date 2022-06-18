package com.fahgutawan.arisans.view

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.ui.theme.Typography
import com.fahgutawan.arisans.util.MyTopBar
import com.tahutelor.arisans.ui.theme.GrayDark

@Composable
fun AddArisanPage() {

    AddTopBar()
}

@Composable
fun AddContent() {
    val height = LocalConfiguration.current.screenHeightDp
    val scaledHeight = height / 10
    var scrollState = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(state = scrollState, orientation = Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //To prevent some compose rendered behind our top bar
            Spacer(modifier = Modifier.height((scaledHeight + 16).dp))
        }
    }
}

@Composable
fun AddTopBar() {
    MyTopBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_registernext_back),
                    contentDescription = "Back",
                    tint = GrayDark
                )
            }

            Text(
                text = "Buat Kelompok Arisan",
                style = Typography.h1,
                textAlign = TextAlign.Center,
                color = GrayDark
            )
        }
    }
}