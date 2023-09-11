package com.base.core.data.repository.todo


import com.base.core.model.network.Resource
import com.base.data.model.TodoItem
import kotlinx.coroutines.flow.Flow


interface TodoRepository {
    fun getDataWithFlow(): Flow<Resource<List<TodoItem>>>
}