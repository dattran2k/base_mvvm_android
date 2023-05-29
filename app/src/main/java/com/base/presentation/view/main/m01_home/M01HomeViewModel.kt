package com.base.presentation.view.main.m01_home

import androidx.lifecycle.viewModelScope
import com.base.data.network.Resource
import com.base.data.respository.DemoRepository
import com.base.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Why ViewModel state  difference than this project from Google : https://github.com/dattran2k/nowinandroid/tree/main/feature
 * =======================================================================================
 * Data  of "now in android" project is listen from local (Room, DataStore) using Flow
 * check https://developer.android.com/topic/libraries/architecture/datastore?hl=vi
 * and https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?hl=vi#0
 * that's mean if database data changed, flow will emit new data, and ViewModel state ( homeUiState ) will able to collect that
 * and if you want to refresh new data ?
 * 1.UI action ->
 * 2.Request API for new data ->
 * 3.New data will be save in Room or DataStore ->
 * 4.Flow Room or DataStore will emit new value ->
 * 5.Flow in ViewModel that collect from that flow will map and produce new UI state .
 * Check out : https://github.com/dattran2k/nowinandroid/blob/main/feature/foryou/src/main/java/com/google/samples/apps/nowinandroid/feature/foryou/ForYouViewModel.kt
 * That's why it difference. One day, maybe I will create that data flow
 * =========================================================================================
 */
/**
Pick one style getData
 */

@HiltViewModel
class M01HomeViewModel @Inject constructor(val demoRepository: DemoRepository) : BaseViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        updateData()
    }

    fun updateData() {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            // This still have too much boilerplate code, how to improve this ?
            demoRepository.getDataWithFlow().map {
                when (it) {
                    is Resource.Error -> HomeUiState.Error(it.message)
                    Resource.Loading -> HomeUiState.Loading
                    is Resource.Success -> HomeUiState.Success(it.data)
                }
            }.collect {
                _homeUiState.emit(it)
            }
        }
    }

    // getDataWithoutFlow will not emit Resource.Loading , so we have to emit HomeUiState.Loading ourself
    fun getDataStyle2() {
        viewModelScope.launch {
            _homeUiState.emit(HomeUiState.Loading)
            demoRepository.getDataWithoutFlow().let {
                _homeUiState.emit(
                    when (it) {
                        is Resource.Error -> HomeUiState.Error(it.message)
                        Resource.Loading -> HomeUiState.Loading
                        is Resource.Success -> HomeUiState.Success(it.data)
                    }
                )
            }
        }
    }
}
