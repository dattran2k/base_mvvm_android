package com.base.presentation

import androidx.lifecycle.ViewModel
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val TAG = this.javaClass.name
    override fun onCleared() {
        super.onCleared()
        Timber.e("$TAG + onViewModelCleared")
    }
}