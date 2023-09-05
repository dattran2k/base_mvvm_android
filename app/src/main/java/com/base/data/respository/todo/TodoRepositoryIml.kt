package com.base.data.respository.todo

import com.base.data.network.Resource
import com.base.data.network.flowSafeApiCall
import com.base.di.Dispatcher
import com.base.di.MyDispatchers
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
