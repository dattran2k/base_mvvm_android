package vn.vtvnews.network.data_source

import android.content.Context
import vn.vtvnews.network.service.VtvNewsService
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val vtvNewsService: VtvNewsService,
    context: Context
) : BaseDataSource(context) {
    suspend fun getHabitSite() = safeApiCall { vtvNewsService.getHabitSite(deviceId, userId) }

}