package com.fahgutawan.arisans.model

data class UserData(
    val body: UserDataBody
)

data class UserDataBody(
    val NamaLengkap:String,
    val NomorTelepon:String,
    val LinkFoto:String,
    val NomorKTP:String
)