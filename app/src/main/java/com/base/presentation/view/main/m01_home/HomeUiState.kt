package com.base.presentation.view.main.m01_home


sealed interface HomeUiState {
    class Success(val data: String) : HomeUiState
    class Error(val msg: String?) : HomeUiState
    object Loading : HomeUiState
}