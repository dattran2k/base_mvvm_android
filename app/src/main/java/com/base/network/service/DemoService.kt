package com.base.network.service

import com.base.network.ApiResponseData
import com.base.network.Endpoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DemoService {
    @GET(Endpoint.GET_DEMO)
    suspend fun getDemo(): Response<ApiResponseData<Any>>

}