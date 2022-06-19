package com.fahgutawan.arisans.repo

import com.fahgutawan.arisans.api.RetrofitInstance
import com.fahgutawan.arisans.model.LoginPost
import com.fahgutawan.arisans.model.LoginResponseModel
import com.fahgutawan.arisans.model.RegisterPost
import com.fahgutawan.arisans.model.RegisterResponseModel
import retrofit2.Response

class ArisansApiRepo {
    suspend fun postRegister(regData:RegisterPost):Response<RegisterResponseModel>{
        return RetrofitInstance.arisansApi.postRegister(regData)
    }
    suspend fun postLogin(loginData:LoginPost):Response<LoginResponseModel>{
        return RetrofitInstance.arisansApi.postLogin(loginData)
    }
}