package com.base.presentation.view.m01_home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.data.network.Resource
import com.base.data.respository.todo.TodoRepository
import com.base.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class M01HomeViewModel @Inject constructor(private val todoRepository: TodoRepository) : BaseViewModel() {

    val homeUIState = MutableLiveData<HomeUIState>()

    init {
        getData()
    }

    fun refreshData() {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            todoRepository.getDataWithFlow().collect {
                homeUIState.value = when (it) {
                    is Resource.Error -> HomeUIState.Error(it.message)
                    is Resource.Loading -> HomeUIState.Loading
                    is Resource.Success -> HomeUIState.Success(it.data)
                }
            }
        }
    }
}
