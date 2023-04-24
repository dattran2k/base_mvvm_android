package com.base.presentation.view.main.m01_home

import androidx.lifecycle.viewModelScope
import com.base.base.BaseViewModel
import com.base.base.CommonState
import com.base.network.ApiResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class M01HomeViewModel @Inject constructor(private val homeRepo: HomeRepository) : BaseViewModel() {
    val homeState = MutableStateFlow<CommonState<ApiResponseData<Any>>>(CommonState.Init())
    fun getData() {
        viewModelScope.launch {
            homeState.updateCommonState(homeRepo.getHabitSites())
        }
    }

}

