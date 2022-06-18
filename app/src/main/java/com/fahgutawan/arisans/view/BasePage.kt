package com.fahgutawan.arisans.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.fahgutawan.arisans.navigation.BaseLayerNav
import com.fahgutawan.arisans.util.MyBottomMenu
import com.tahutelor.arisans.ui.theme.White
import kotlinx.coroutines.CoroutineScope

@Composable
fun BasePage(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    val height = LocalConfiguration.current.screenHeightDp
    val bottomNavBarHeight = height / 8

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = White
    ) {
        val baseNavController = rememberNavController()

        //For the content
        Column(modifier = Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((height - bottomNavBarHeight).dp),
                color = White
            ) {
                BaseLayerNav(
                    scope = scope,
                    scaffoldState = scaffoldState,
                    navController = baseNavController
                )
            }

            //Bottom navBar
            MyBottomMenu(
                height = bottomNavBarHeight.dp,
                scope = scope,
                navController = baseNavController
            )
        }
    }
}