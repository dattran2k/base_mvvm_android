package com.base.network.data_source

import android.content.Context
import com.base.network.service.DemoService
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val DemoService: DemoService,
    context: Context
) : BaseDataSource(context) {
    suspend fun getDemo() = safeApiCall { DemoService.getDemo() }

}