package com.base.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.data.network.Resource
import com.base.presentation.view.main.m01_home.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

open class BaseViewModel : ViewModel() {
    suspend fun delayLoadingAtLeast(timeStart: Long, timeEnd: Long, atLeastSec: Int) {
        val atLeast = atLeastSec * 1000 - (timeEnd - timeStart)
        if (atLeast > 0)
            delay(atLeast)
    }

    fun getCurrentTime() = Calendar.getInstance().time.time

}