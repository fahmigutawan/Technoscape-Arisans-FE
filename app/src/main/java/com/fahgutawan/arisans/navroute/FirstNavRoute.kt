package com.fahgutawan.arisans.navroute

sealed class FirstNavRoute(val route:String){
    object SplashScr:FirstNavRoute("first_splash")
    object LoginScr:FirstNavRoute("login_scr")
    object RegisterScr:FirstNavRoute("register_scr")
    object RegisterNextScr:FirstNavRoute("registernext_scr")
    object BaseScr:FirstNavRoute("base_scr")
    object AddArisanScr:FirstNavRoute("addarisan_scr")
}
