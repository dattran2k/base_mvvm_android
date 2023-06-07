package com.base.data.respository

import com.base.core.common.Resource
import com.base.data.model.TodoItem
import kotlinx.coroutines.flow.Flow


interface DemoRepository {
    fun getDataWithFlow(): Flow<Resource<List<TodoItem>>>
}