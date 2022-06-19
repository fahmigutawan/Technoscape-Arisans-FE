package com.fahgutawan.arisans.api

import com.fahgutawan.arisans.model.*
import com.fahgutawan.arisans.myViewModel
import retrofit2.Response
import retrofit2.http.*

interface ArisansApi {
    //REGISTER
    @POST("/user/register")
    suspend fun postRegister(
        @Body regData: RegisterPost
    ): Response<RegisterResponseModel>

    //LOGIN
    @POST("/user/login")
    suspend fun postLogin(
        @Body regData: LoginPost
    ): Response<LoginResponseModel>

    //MAKING NEW ARISAN
    @POST("/arisan/register")
    suspend fun postNewArisan(
        @Body newArisan: NewArisan
    ): Response<NewArisan>

    //LOADING USER DATA
    @GET("/user")
    suspend fun getUserData():Response<UserData>

    //List Arisan
    @GET("/arisan")
    suspend fun getListArisan():Response<HomeArisan>

    //Get Specific Arisan
    @GET("/join/{id_arisan}")
    suspend fun getDetailArisan(
        @Path("id_arisan") ID:Int
    ):Response<DetailArisanResponse>
}