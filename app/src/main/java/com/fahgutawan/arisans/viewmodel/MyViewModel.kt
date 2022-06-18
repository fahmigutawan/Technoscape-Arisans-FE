package com.fahgutawan.arisans.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.repo.ArisansApiRepo

class MyViewModel(val repo: ArisansApiRepo) : ViewModel() {
    //Login Page Attr
    val loginTelp = mutableStateOf("")
    val loginPass = mutableStateOf("")

    //Register Page Attr
    val registerTelp = mutableStateOf("")
    val registerPass = mutableStateOf("")

    //Base Bottom Menu's Stuff
    val isHomeSelected = mutableStateOf(true)
    val isRiwayatSelected = mutableStateOf(false)
    val isAddArisanSelected = mutableStateOf(false)
    val isRewardSelected = mutableStateOf(false)
    val isProfileSelected = mutableStateOf(false)
    var icHomeIcon = mutableStateOf(R.drawable.ic_botmenu_home_unselected)
    var icHistoryIcon = mutableStateOf(R.drawable.ic_botmenu_history_unselected)
    var icAddIcon = mutableStateOf(R.drawable.ic_botmenu_add_selected)
    var icRewardIcon = mutableStateOf(R.drawable.ic_botmenu_home_unselected)
    var icProfileIcon = mutableStateOf(R.drawable.ic_botmenu_home_unselected)
    fun resetBotMenuSelectState(){
        isHomeSelected.value = false
        isRiwayatSelected.value = false
        isAddArisanSelected.value = false
        isRewardSelected.value = false
        isProfileSelected.value = false
    }
}