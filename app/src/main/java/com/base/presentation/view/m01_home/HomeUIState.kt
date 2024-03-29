package com.base.presentation.view.m01_home

import com.base.data.model.TodoItem


sealed interface HomeUIState {
    class Success(val data: List<TodoItem>) : HomeUIState
    class Error(val msg: String?) : HomeUIState
    data object Loading : HomeUIState
}