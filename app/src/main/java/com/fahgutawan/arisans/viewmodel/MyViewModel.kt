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
import com.fahgutawan.arisans.interfaces.*
import com.fahgutawan.arisans.model.*
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.repo.ArisansApiRepo
import com.fahgutawan.arisans.snackbarHostState
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewModel(val repo: ArisansApiRepo) : ViewModel() {
    //User info
    val namaLengkap = mutableStateOf("...")
    val noTelp = mutableStateOf("...")
    val linkFoto = mutableStateOf("...")
    val noKtp = mutableStateOf("...")

    //Preloaded
    val userPicture = mutableStateOf(R.drawable.ic_change_profile_background)
    val userToken = mutableStateOf("")
    var listArisan = mutableStateListOf<HomeArisanBody>()

    //MainActivity
    val isLoading = mutableStateOf(false)
    fun showSnackbar(text: String) {
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
    val isHomeSelected = mutableStateOf(false)
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
    val homeListOfBanner = mutableStateListOf<Uri>()
    val homeIsBannerLoaded = mutableStateOf(false)

    //val homeListOfArisanPicked = mutableStateListOf<>()
    val homeIsArisanLocked = mutableStateOf(true)
    val homeTest = mutableStateOf(3)
    fun loadListOfBanner() {
        val storageRef = FirebaseStorage
            .getInstance()
            .getReference()

        storageRef
            .child("/banner")
            .listAll()
            .addOnSuccessListener {
                it.items.forEach { ref ->
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        homeListOfBanner.add(uri)
                    }
                }
                homeIsBannerLoaded.value = true
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
    val addJenisBank = mutableStateOf("")
    val addBuktiPembayaran = mutableStateOf<Uri?>(null)
    val addBuktiPembayaranValue = mutableStateOf("")
    val addIsAddBerhasil = mutableStateOf(false)

    //Reward Attr
    val rewardNominal = mutableStateOf("...")

    //Detail arisan
    val arisanPicked = mutableStateOf<HomeArisanBody?>(null)

    /**[API STUFF]*/
    fun postRegister(regData: RegisterPost, i: RegisterInterface) {
        viewModelScope.launch {
            val res = repo.postRegister(regData)
            if (res.isSuccessful) {
                if(res.body()!!.success){
                    i.onTokenRetrieved(res.body()!!.body.token)
                }else{
                    i.onFailed()
                }
            } else {
                i.onFailed()
            }
        }
    }
    fun postLogin(loginData: LoginPost, i: LoginInterface) {
        viewModelScope.launch {
            val res = repo.postLogin(loginData)
            if (res.isSuccessful) {
                if(res.body()!!.success){
                    i.onTokenRetrieved(res.body()!!.body.token)
                }else{
                    i.onFailed()
                }
            } else {
                i.onFailed()
            }
        }
    }
    fun postNewArisan(newArisan: NewArisan, i:NewArisanInterface){
        viewModelScope.launch {
            val res = repo.postNewArisan(newArisan)
            if(res.isSuccessful){
                i.onArisanAdded()
            }else{
                i.onFailed()
            }

            Log.e("ASDASD", res.toString())
        }
    }
    fun getUserData(i:UserDataInterface){
        viewModelScope.launch {
            val res = repo.getUserData()

            if(res.isSuccessful){
                i.onDataRetrieved(res.body()!!)
            }
        }
    }
    fun getListArisan(){
        viewModelScope.launch {
            val res = repo.getListArisan()

            if(res.isSuccessful){
                if(res.body()!!.success){
                    res.body()!!.body.forEach { arisanBody ->
                        listArisan.add(arisanBody)
                    }
                }else{

                }
            }else{

            }
        }
    }
    fun getDetailArisan(ID:Int, i:DetailArisanInterface){
        viewModelScope.launch {
            val res = repo.getDetailArisan(ID)

            if(res.isSuccessful){
                if(res.body()!!.success){
                    i.onArisanRetrieved(ID)
                }else{
                    i.onFailed()
                }
            }else{
                i.onFailed()
            }
        }
    }
}