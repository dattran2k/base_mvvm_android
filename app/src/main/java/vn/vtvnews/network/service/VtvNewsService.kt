package vn.vtvnews.network.service

import vn.vtvnews.network.ApiResponseData
import vn.vtvnews.network.Endpoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VtvNewsService {
    @GET(Endpoint.GET_HABIT_SITES)
    suspend fun getHabitSite(
        @Query("deviceid") deviceId: String,
        @Query("userid") userId: String
    ): Response<ApiResponseData<List<Any>>>

}