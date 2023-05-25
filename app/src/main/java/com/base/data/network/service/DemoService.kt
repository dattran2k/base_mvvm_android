package com.base.data.network.service

import com.base.data.network.Endpoint
import retrofit2.Response
import retrofit2.http.GET

interface DemoService {
    @GET(Endpoint.GET_DEMO)
    suspend fun getDemo(): Response<String>

}