package com.fahgutawan.arisans.interfaces

interface RegisterInterface {
    suspend fun onTokenRetrieved(token:String)
    suspend fun onFailed()
}