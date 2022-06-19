package com.fahgutawan.arisans.repo

import com.fahgutawan.arisans.api.RetrofitInstance
import com.fahgutawan.arisans.model.*
import retrofit2.Call
import retrofit2.Response

class ArisansApiRepo {
    suspend fun postRegister(regData:RegisterPost):Response<RegisterResponseModel>{
        return RetrofitInstance.arisansApi.postRegister(regData)
    }
    suspend fun postLogin(loginData:LoginPost):Response<LoginResponseModel>{
        return RetrofitInstance.arisansApi.postLogin(loginData)
    }
    suspend fun postNewArisan(newArisan: NewArisan):Response<NewArisan>{
        return RetrofitInstance.arisansApi.postNewArisan(newArisan)
    }

    suspend fun getUserData():Response<UserData>{
        return RetrofitInstance.arisansApi.getUserData()
    }
    suspend fun getListArisan():Response<HomeArisan>{
        return RetrofitInstance.arisansApi.getListArisan()
    }
    suspend fun getDetailArisan(ID:Int):Response<DetailArisanResponse>{
        return RetrofitInstance.arisansApi.getDetailArisan(ID)
    }
}