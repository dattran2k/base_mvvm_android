package com.base.data.network.data_source

import com.base.data.network.Resource
import com.base.data.network.service.DemoService
import com.base.di.Dispatcher
import com.base.di.NiaDispatchers
import com.base.util.InternetUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val demoService: DemoService,
) {
    suspend fun getDemo( ) = demoService.getDemo()
}

