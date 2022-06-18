package com.fahgutawan.arisans.navroute

sealed class FirstNavRoute(val route:String){
    object SplashScr:FirstNavRoute("first_splash")
    object LoginScr:FirstNavRoute("login_scr")
    object RegisterScr:FirstNavRoute("register_scr")
}
