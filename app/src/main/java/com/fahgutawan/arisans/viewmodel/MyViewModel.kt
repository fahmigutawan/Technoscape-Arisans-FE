package com.fahgutawan.arisans.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.repo.ArisansApiRepo

class MyViewModel(val repo:ArisansApiRepo):ViewModel() {
    //Login Page Attr
    val loginTelp = mutableStateOf("")
    val loginPass = mutableStateOf("")

    //Register Page Attr
    val registerTelp = mutableStateOf("")
    val registerPass = mutableStateOf("")
}