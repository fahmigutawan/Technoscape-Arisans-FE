package com.fahgutawan.arisans.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.interfaces.LoginInterface
import com.fahgutawan.arisans.interfaces.RegisterInterface
import com.fahgutawan.arisans.model.ArisanPesertaArisan
import com.fahgutawan.arisans.model.LoginPost
import com.fahgutawan.arisans.model.RegisterPost
import com.fahgutawan.arisans.model.RegisterResponseModel
import com.fahgutawan.arisans.repo.ArisansApiRepo
import com.fahgutawan.arisans.snackbarHostState
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewModel(val repo: ArisansApiRepo) : ViewModel() {
    //MainActivity
    val isLoading = mutableStateOf(false)
    fun showSnackbar(text:String){
        viewModelScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(text, duration = SnackbarDuration.Short)
        }
    }

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

    //Arisan Page
    val arisanIsAdmin = mutableStateOf(false)
    val arisanName = mutableStateOf("...")
    val arisanId = mutableStateOf("#123456")
    val arisanPass = mutableStateOf("ABCDEF")
    val arisanPesertaArisan = mutableStateListOf(
        ArisanPesertaArisan("Joko", true),
        ArisanPesertaArisan("Widiyanto", false),
        ArisanPesertaArisan("Michael", false),
        ArisanPesertaArisan("Nadila Zahro", true),
        ArisanPesertaArisan("Armia Zuraida", true),
        ArisanPesertaArisan("Erlina Novitasari", false),
        ArisanPesertaArisan("Ryo Shandi", false)
    )

    //Add arisan page
    val addNama = mutableStateOf("")
    val addJumlah = mutableStateOf(2)
    val addNominal = mutableStateOf("")
    val addStatus = mutableStateOf("")

    /**[API STUFF]*/
    fun postRegister(regData: RegisterPost, i: RegisterInterface) {
        viewModelScope.launch {
            val res = repo.postRegister(regData)
            if (res.isSuccessful) {
                i.onTokenRetrieved(res.body()!!.body.token)
            } else {
                i.onFailed()
            }
        }
    }

    fun postLogin(loginData: LoginPost, i: LoginInterface) {
        viewModelScope.launch {
            val res = repo.postLogin(loginData)
            if (res.isSuccessful) {
                i.onTokenRetrieved(res.body()!!.body.token)
            } else {
                i.onFailed()
            }
        }
    }
}