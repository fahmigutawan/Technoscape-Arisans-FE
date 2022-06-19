package com.fahgutawan.arisans.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.view.*
import com.tahutelor.arisans.view.SplashScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FirstLayerNav(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FirstNavRoute.SplashScr.route
    ) {
        composable(route = FirstNavRoute.SplashScr.route) {
            SplashScreen(scope = scope, navController = navController)
        }
        composable(route = FirstNavRoute.LoginScr.route) {
            LoginPage(scope = scope, navController = navController, scaffoldState = scaffoldState)
        }
        composable(route = FirstNavRoute.RegisterScr.route) {
            RegisterPage(
                scope = scope,
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(route = FirstNavRoute.RegisterNextScr.route) {
            RegisterNextPage(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        }
        composable(route = FirstNavRoute.BaseScr.route) {
            BasePage(
                scope = rememberCoroutineScope(),
                scaffoldState = scaffoldState,
                firstLayerNavController = navController
            )
        }
        composable(route = FirstNavRoute.AddArisanScr.route) {
            AddArisanPage(scope, scaffoldState, navController)
        }
    }
}