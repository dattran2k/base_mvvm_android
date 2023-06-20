package com.base.data.respository.todo


import com.base.core.common.Resource
import com.base.data.model.TodoItem
import kotlinx.coroutines.flow.Flow


interface TodoRepository {
    fun getDataWithFlow(): Flow<Resource<List<TodoItem>>>
}