package com.astro.repo

import com.astro.api.AstroApi
import com.astro.client.RetroFitClient
import com.astro.data.AstroResponse
import com.astro.data.User

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

class AstroApiRepo {

    private val astroApiSerivce = RetroFitClient.getInstance().create(AstroApi::class.java)

  //  private val _astroResponse = MutableStateFlow(Response.success(AstroResponse("","",null)))


    // private val _astroResponse = MutableStateFlow<Response<AstroResponse>>(null)
   // val astroResponse: StateFlow<Response<AstroResponse>>
    //    get() = _astroResponse

    suspend fun getAstroNum(user : User): Response<AstroResponse> {
        val response = astroApiSerivce.postAstroNum(user);
        return response;
    }



}