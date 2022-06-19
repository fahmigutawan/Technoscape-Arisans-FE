package com.fahgutawan.arisans.api

import com.fahgutawan.arisans.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ArisansApi {
    //Login and Register
    @POST("/user/register")
    suspend fun postRegister(
        @Body regData: RegisterPost
    ): Response<RegisterResponseModel>

    @POST("/user/login")
    suspend fun postLogin(
        @Body regData: LoginPost
    ): Response<LoginResponseModel>

    @GET("")
    suspend fun loadRandomArisanList():Response<List<HomeArisan>>
}