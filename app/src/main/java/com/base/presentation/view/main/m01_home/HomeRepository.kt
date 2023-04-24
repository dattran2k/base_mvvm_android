package com.base.presentation.view.main.m01_home

import com.base.network.data_source.ApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiDataSource: ApiDataSource) {

    suspend fun getHabitSites() = flow {
        emit(apiDataSource.getDemo())
    }.flowOn(Dispatchers.IO)
}
