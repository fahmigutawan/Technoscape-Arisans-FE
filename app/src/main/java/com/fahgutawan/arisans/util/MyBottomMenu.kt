package com.fahgutawan.arisans.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cisu.cisusplash.util.Blue
import com.tahutelor.arisans.ui.theme.GrayLight
import kotlinx.coroutines.CoroutineScope

@Composable
fun MyBottomMenu(height: Dp, scope: CoroutineScope, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        color = GrayLight
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

        }
    }
}