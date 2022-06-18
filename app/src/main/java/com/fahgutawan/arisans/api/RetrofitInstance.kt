package com.fahgutawan.arisans.api

import com.fahgutawan.arisans.util.ApiUtil.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val arisansApi: ArisansApi by lazy {
        retrofit.create(ArisansApi::class.java)
    }
}