package com.base.presentation.view.main.m01_home

import androidx.lifecycle.viewModelScope
import com.base.data.network.Resource
import com.base.data.respository.DemoRepository
import com.base.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class M01HomeViewModel @Inject constructor(val demoRepository: DemoRepository) : BaseViewModel() {
    private val _homeState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeState = _homeState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _homeState.emit(HomeUiState.Loading)
            demoRepository.getDemo().collectLatest {
                _homeState.emit(
                    if (it.data != null && it.status == Resource.Status.SUCCESS)
                        HomeUiState.Success(it.data)
                    else
                        HomeUiState.Error(it.message)
                )
            }
        }
    }

}

