package vn.vtvnews.presentation.view.main.m01_home

import vn.vtvnews.network.data_source.ApiDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiDataSource: ApiDataSource) {

    suspend fun getHabitSites() = flow {
        emit(apiDataSource.getHabitSite())
    }
}

