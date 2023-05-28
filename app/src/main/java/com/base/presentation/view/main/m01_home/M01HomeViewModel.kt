package com.base.presentation.view.main.m01_home

import androidx.lifecycle.viewModelScope
import com.base.data.network.Resource
import com.base.data.respository.DemoRepository
import com.base.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.concurrent.thread

@HiltViewModel
class M01HomeViewModel @Inject constructor(val demoRepository: DemoRepository) : BaseViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState
    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            // This still have too much boilerplate code, how to improve this ?
            demoRepository.getDemo().map {
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
}
