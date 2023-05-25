package com.base.data.network.data_source

import android.content.Context
import com.base.data.network.service.DemoService
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val demoService: DemoService
) : BaseDataSource() {
    suspend fun getDemo() = safeApiCall { demoService.getDemo() }

}