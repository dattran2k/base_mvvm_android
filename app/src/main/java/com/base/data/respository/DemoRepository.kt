package com.base.data.respository

import com.base.core.common.Resource
import kotlinx.coroutines.flow.Flow


interface DemoRepository {
    fun getDataWithFlow(): Flow<Resource<String>>
}