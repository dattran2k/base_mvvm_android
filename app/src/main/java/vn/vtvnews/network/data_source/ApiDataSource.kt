package vn.vtvnews.network.data_source

import android.content.Context
import vn.vtvnews.network.service.PegaService
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val pegaService: PegaService,
    context: Context
) : BaseDataSource(context) {
    suspend fun getHabitSite() = safeApiCall { pegaService.getHabitSite(deviceId, userId) }

}