package com.news.thanhnien.network.service

import com.news.thanhnien.network.ApiResponseData
import com.news.thanhnien.network.Endpoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PegaService {
    @GET(Endpoint.GET_HABIT_SITES)
    suspend fun getHabitSite(
        @Query("deviceid") deviceId: String,
        @Query("userid") userId: String
    ): Response<ApiResponseData<List<Any>>>

}