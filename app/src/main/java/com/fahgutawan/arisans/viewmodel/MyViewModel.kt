package com.fahgutawan.arisans.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.repo.ArisansApiRepo
import kotlinx.coroutines.launch

class MyViewModel(val repo: ArisansApiRepo) : ViewModel() {
    //Login Page Attr
    val loginTelp = mutableStateOf("")
    val loginPass = mutableStateOf("")

    //Register Page Attr
    val registerTelp = mutableStateOf("")
    val registerPass = mutableStateOf("")

    //RegisterNext Page Attr
    val registerNextUsername = mutableStateOf("")
    val registerNextTTL = mutableStateOf("")
    val registerNextNIK = mutableStateOf("")
    val registerNextImagePicked = mutableStateOf<Uri?>(null)

    //Base Bottom Menu's Stuff
    val isHomeSelected = mutableStateOf(true)
    val isRiwayatSelected = mutableStateOf(false)
    val isAddArisanSelected = mutableStateOf(false)
    val isRewardSelected = mutableStateOf(false)
    val isProfileSelected = mutableStateOf(false)
    val icHomeIcon = mutableStateOf(R.drawable.ic_botmenu_home_unselected)
    val icHistoryIcon = mutableStateOf(R.drawable.ic_botmenu_history_unselected)
    val icAddIcon = mutableStateOf(R.drawable.ic_botmenu_add_selected)
    val icRewardIcon = mutableStateOf(R.drawable.ic_botmenu_home_unselected)
    val icProfileIcon = mutableStateOf(R.drawable.ic_botmenu_home_unselected)
    fun resetBotMenuSelectState() {
        isHomeSelected.value = false
        isRiwayatSelected.value = false
        isAddArisanSelected.value = false
        isRewardSelected.value = false
        isProfileSelected.value = false
    }

    //Base HomePage
    val homeUsername = mutableStateOf(". . .")
    val homeUserPhoto = mutableStateOf(R.drawable.ic_home_photo_unloaded)
    val homeSearchValue = mutableStateOf("")
    val homeListOfBanner = mutableStateListOf<Int>()
    val homeIsRandomLoaded = mutableStateOf(false)
    val homeIsArisanLocked = mutableStateOf(false)
    fun loadUserPhoto() {
        viewModelScope.launch {

        }
    }

    //Landing Arisan
    val landArisanTotalGet = mutableStateOf("...")
    val landArisanSyarat = mutableStateOf("...")
    val landArisanIsLocked = mutableStateOf(false)
    val landArisanNama = mutableStateOf("...")
    val landTotalOrang = mutableStateOf("...")
    val landArisanUang = mutableStateOf("...")
    val landArisanKode = mutableStateOf("#123456")
    val landArisanIsAcceptTerm = mutableStateOf(false)

}