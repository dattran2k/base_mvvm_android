package com.base.data.respository

import com.base.data.network.Resource
import kotlinx.coroutines.flow.Flow



interface DemoRepository {
    fun getDemo(): Flow<Resource<String>>
}