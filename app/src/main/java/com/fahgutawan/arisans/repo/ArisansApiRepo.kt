package com.fahgutawan.arisans.repo

import com.fahgutawan.arisans.api.RetrofitInstance
import com.fahgutawan.arisans.model.*
import retrofit2.Response

class ArisansApiRepo {
    suspend fun postRegister(regData:RegisterPost):Response<RegisterResponseModel>{
        return RetrofitInstance.arisansApi.postRegister(regData)
    }
    suspend fun postLogin(loginData:LoginPost):Response<LoginResponseModel>{
        return RetrofitInstance.arisansApi.postLogin(loginData)
    }
    suspend fun getRandomArisanList():Response<List<HomeArisan>> {
        return RetrofitInstance.arisansApi.loadRandomArisanList()
    }
}