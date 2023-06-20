package com.base.presentation.view.m00_main.m01_home

import com.base.data.model.TodoItem


public sealed interface HomeUIState {
    class Success(val data: List<TodoItem>) : HomeUIState
    class Error(val msg: String?) : HomeUIState
    object Loading : HomeUIState
}