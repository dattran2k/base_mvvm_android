package com.base.data.respository.todo

import com.base.core.common.Resource
import com.base.core.common.flowSafeApiCall
import com.base.core.di.Dispatcher
import com.base.core.di.MyDispatchers
import com.base.data.model.TodoItem
import com.base.data.network.ApiDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TodoRepositoryIml @Inject constructor(
    private val apiDataSource: ApiDataSource,
    @Dispatcher(MyDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) : TodoRepository {
    override fun getDataWithFlow(): Flow<Resource<List<TodoItem>>> {
        return flowSafeApiCall(dispatcher) {
            apiDataSource.getTodos()
        }
    }
}
