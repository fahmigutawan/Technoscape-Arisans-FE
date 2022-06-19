package com.fahgutawan.arisans.interfaces

interface LoginInterface {
    suspend fun onTokenRetrieved(token:String)
    suspend fun onFailed()
}