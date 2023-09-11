package com.base.core.data.repository.todo

import com.base.core.datastore.di.Dispatcher
import com.base.core.datastore.di.MyDispatchers
import com.base.core.model.network.Resource
import com.base.core.network.ApiDataSource
import com.base.core.network.flowSafeApiCall
import com.base.data.model.TodoItem
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
