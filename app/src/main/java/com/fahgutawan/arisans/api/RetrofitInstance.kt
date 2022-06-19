package com.fahgutawan.arisans.api

import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.util.ApiUtil.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().addInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val requestBuilder =
                original.newBuilder().addHeader("Authorization", myViewModel.userToken.value)

            return chain.proceed(requestBuilder.build())
        }
    }).build()

    private val retrofit =
        Retrofit
            .Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val arisansApi: ArisansApi by lazy {
        retrofit.create(ArisansApi::class.java)
    }
}