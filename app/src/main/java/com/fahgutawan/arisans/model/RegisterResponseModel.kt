package com.fahgutawan.arisans.model

data class RegisterResponseModel(val success:Boolean, val body:RegisterBody)
data class RegisterBody(val token:String)