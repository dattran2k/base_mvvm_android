package com.base.data.respository

import com.base.data.network.Resource
import com.base.data.network.data_source.ApiDataSource
import com.base.data.network.toFlowSafeApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DemoRepositoryIml @Inject constructor(
    private val apiDataSource: ApiDataSource,
    val dispatcher: CoroutineDispatcher
) : DemoRepository {
    override suspend fun getDemo(): Flow<Resource<String>> {
        return suspend {
            apiDataSource.getDemo()
        }.toFlowSafeApi(dispatcher)
    }
}
