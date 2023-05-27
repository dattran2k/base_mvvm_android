package com.base.presentation.view.main.m01_home

import androidx.lifecycle.viewModelScope
import com.base.data.network.Resource
import com.base.data.respository.DemoRepository
import com.base.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.concurrent.thread


@HiltViewModel
class M01HomeViewModel @Inject constructor(val demoRepository: DemoRepository) : BaseViewModel() {
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState = _homeUiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            demoRepository.getDemo().onStart {
                _homeUiState.emit(HomeUiState.Loading)
            }.map {
                if (it.data != null && it.status == Resource.Status.SUCCESS)
                    HomeUiState.Success(it.data)
                else
                    HomeUiState.Error(it.message)
            }.collect {
                _homeUiState.emit(it)
            }
        }
    }
}

