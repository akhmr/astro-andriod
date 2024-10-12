package com.astro.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetroFitClient {

    val BASE_URL="http://192.168.29.27:8080/"

    fun getInstance() : Retrofit{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}