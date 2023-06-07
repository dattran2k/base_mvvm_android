package com.base.presentation.view.main.m01_home

import com.base.data.model.TodoItem


sealed interface HomeUiState {
    class Success(val data: List<TodoItem>) : HomeUiState
    class Error(val msg: String?) : HomeUiState
    object Loading : HomeUiState
}