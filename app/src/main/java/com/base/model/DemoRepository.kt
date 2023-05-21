package com.base.model

import com.base.model.network.data_source.ApiDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DemoRepository @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getHabitSites() = withContext(dispatcher) {
        apiDataSource.getDemo()
    }
}