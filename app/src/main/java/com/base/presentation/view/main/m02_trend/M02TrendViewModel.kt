package com.base.presentation.view.main.m02_trend

import com.base.data.respository.DemoRepository
import com.base.data.respository.DemoRepositoryIml
import com.base.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class M02TrendViewModel @Inject constructor(private val demoRepository: DemoRepository) : BaseViewModel() {

}