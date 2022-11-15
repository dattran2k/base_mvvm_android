package com.news.thanhnien.network.data_source

import android.content.Context
import com.news.thanhnien.network.service.PegaService
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val pegaService: PegaService,
    context: Context
) : BaseDataSource(context) {
    suspend fun getHabitSite() = safeApiCall { pegaService.getHabitSite(deviceId, userId) }

}