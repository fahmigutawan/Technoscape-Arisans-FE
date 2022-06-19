package com.fahgutawan.arisans.model

data class LoginResponseModel(val success:Boolean, val body:LoginBody)
data class LoginBody(val token:String)