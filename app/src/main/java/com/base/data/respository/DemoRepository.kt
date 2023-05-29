package com.base.data.respository

import com.base.data.network.Resource
import kotlinx.coroutines.flow.Flow


interface DemoRepository {
    fun getDataWithFlow(): Flow<Resource<String>>
    suspend fun getDataWithoutFlow(): Resource<String>
}