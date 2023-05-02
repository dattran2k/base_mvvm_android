package com.base.model.network.service

import com.base.model.network.Endpoint
import retrofit2.Response
import retrofit2.http.GET

interface DemoService {
    @GET(Endpoint.GET_DEMO)
    suspend fun getDemo(): Response<String>

}