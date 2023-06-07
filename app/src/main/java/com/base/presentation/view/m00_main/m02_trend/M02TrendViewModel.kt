package com.base.presentation.view.m00_main.m02_trend

import androidx.lifecycle.ViewModel
import com.base.data.respository.DemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class M02TrendViewModel @Inject constructor(private val demoRepository: DemoRepository) : ViewModel() {

}