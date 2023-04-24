package com.base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.network.ApiResponseData
import com.base.network.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import java.util.*

open class BaseViewModel : ViewModel() {
    suspend fun delayLoadingAtLeast(timeStart: Long, timeEnd: Long, atLeastSec: Int) {
        val atLeast = atLeastSec * 1000 - (timeEnd - timeStart)
        if (atLeast > 0)
            delay(atLeast)
    }

    fun getCurrentTime() = Calendar.getInstance().time.time

    fun <R : Any> MutableLiveData<CommonState<R>>.updateCommonState(response: Resource<R>) {
        value = if (response.status == Resource.Status.SUCCESS && response.data != null) {
            CommonState.Success(response.data)
        } else {
            CommonState.Error(response.message)
        }
    }

    suspend fun <R : Any> MutableStateFlow<CommonState<R>>.updateCommonState(
        flow: Flow<Resource<R>>,
        delayTime: Int = 0,
    ) {
        val startTime = getCurrentTime()
        value = CommonState.Loading()
        flow.collect { response ->
            value = if (response.status == Resource.Status.SUCCESS && response.data != null) {
                if (delayTime > 0)
                    delayLoadingAtLeast(startTime, getCurrentTime(), delayTime)
                CommonState.Success(response.data)
            } else {
                CommonState.Error(response.message)
            }
        }
    }
}