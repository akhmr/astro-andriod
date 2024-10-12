package com.astro.api


import com.astro.data.AstroResponse
import com.astro.data.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AstroApi {

    @POST("api/astro/num/v1")
    suspend fun postAstroNum(@Body user: User): Response<AstroResponse>

    @GET("health")
    suspend fun getAstroHealth(): Response<ResponseBody>

}