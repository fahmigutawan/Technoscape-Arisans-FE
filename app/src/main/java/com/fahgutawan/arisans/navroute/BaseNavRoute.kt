package com.fahgutawan.arisans.navroute

sealed class BaseNavRoute(val route:String) {
    object HomePage:BaseNavRoute("home_base")
    object HistoryPage:BaseNavRoute("history_base")
    object AddPage:BaseNavRoute("add_base")
    object RewardPage:BaseNavRoute("reward_base")
    object ProfilePage:BaseNavRoute("profile_base")
}