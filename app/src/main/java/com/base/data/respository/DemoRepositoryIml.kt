package com.base.data.respository

import com.base.data.network.Resource
import com.base.data.network.data_source.ApiDataSource
import com.base.data.network.flowSafeApiCall
import com.base.data.network.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
Pick one style
 */
class DemoRepositoryIml @Inject constructor(
    private val apiDataSource: ApiDataSource,
    val dispatcher: CoroutineDispatcher,
) : DemoRepository {
    override fun getDataWithFlow(): Flow<Resource<String>> {
        return flowSafeApiCall(dispatcher) {
            apiDataSource.getDemo()
        }
    }

    override suspend fun getDataWithoutFlow(): Resource<String> {
        return safeApiCall(dispatcher) {
            apiDataSource.getDemo()
        }
    }
}
