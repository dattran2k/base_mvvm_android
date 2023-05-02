package com.base.model.network

import com.base.model.network.data_source.ApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DemoRepository @Inject constructor(private val apiDataSource: ApiDataSource) {
    suspend fun getHabitSites() = flow {
        emit(apiDataSource.getDemo())
    }.flowOn(Dispatchers.IO)
}