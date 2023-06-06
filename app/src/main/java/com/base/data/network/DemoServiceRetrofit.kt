package com.base.data.network

import retrofit2.Response
import retrofit2.http.GET

interface DemoServiceRetrofit {
    @GET("Demo")
    suspend fun getDemo(): Response<String>

}