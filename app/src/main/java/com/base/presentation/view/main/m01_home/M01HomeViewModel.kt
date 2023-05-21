package com.base.presentation.view.main.m01_home

import androidx.lifecycle.viewModelScope
import com.base.model.DemoRepository
import com.base.presentation.base.BaseViewModel
import com.base.presentation.base.CommonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class M01HomeViewModel @Inject constructor(val demoRepository: DemoRepository) : BaseViewModel() {
    private val _homeState = MutableStateFlow<CommonState<Any>>(CommonState.Init)
    val homeState = _homeState.asStateFlow()
    init {
        getData()
    }
    fun getData() {
        viewModelScope.launch {
            _homeState.updateCommonState(demoRepository.getHabitSites())
        }
    }

}

