package com.fahgutawan.arisans.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fahgutawan.arisans.navroute.BaseNavRoute
import com.fahgutawan.arisans.view.HomePage
import kotlinx.coroutines.CoroutineScope

@Composable
fun BaseLayerNav(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    firstLayerNavController: NavController,
    baseNavController: NavHostController
) {
    NavHost(
        navController = baseNavController,
        startDestination = BaseNavRoute.HomePage.route
    ) {
        composable(route = BaseNavRoute.HomePage.route) {
            HomePage(
                scope = scope,
                scaffoldState = scaffoldState,
                firstLayerNavController = firstLayerNavController,
                baseNavController = baseNavController
            )
        }
        composable(route = BaseNavRoute.HistoryPage.route) {

        }
        composable(route = BaseNavRoute.AddPage.route) {

        }
        composable(route = BaseNavRoute.RewardPage.route) {

        }
        composable(route = BaseNavRoute.ProfilePage.route) {

        }
    }
}