package com.base.data.respository

import com.base.data.network.Resource
import com.base.data.network.data_source.ApiDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DemoRepositoryIml @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val dispatcher: CoroutineDispatcher,
) : DemoRepository {
    override fun getDemo(): Flow<Resource<String>> {
        return flow {
            emit(apiDataSource.getDemo())
        }.flowOn(dispatcher)
    }

}