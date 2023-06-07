package com.base.data.respository

import com.base.core.common.Resource
import com.base.data.network.ApiDataSource
import com.base.core.common.flowSafeApiCall
import com.base.data.model.TodoItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
Pick one style
 */
class DemoRepositoryIml @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val dispatcher: CoroutineDispatcher,
) : DemoRepository {
    override fun getDataWithFlow(): Flow<Resource<List<TodoItem>>> {
        return flowSafeApiCall(dispatcher) {
            apiDataSource.getTodos()
        }
    }
}
