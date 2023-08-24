package com.base.presentation.view.m00_main.m01_home

import androidx.lifecycle.viewModelScope
import com.base.core.common.Resource
import com.base.data.respository.todo.TodoRepository
import com.base.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class M01HomeViewModel @Inject constructor(private val todoRepository: TodoRepository) : BaseViewModel() {

    private val _homeUIState = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val homeUIState = _homeUIState.asStateFlow()

    init {
        getData()
    }

    fun updateData() {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            // This still have too much boilerplate code, how to improve this ?
            todoRepository.getDataWithFlow().collect {
                _homeUIState.emit(
                    when (it) {
                        is Resource.Error -> HomeUIState.Error(it.message)
                        Resource.Loading -> HomeUIState.Loading
                        is Resource.Success -> HomeUIState.Success(it.data)
                    }
                )
            }
        }
    }
}
