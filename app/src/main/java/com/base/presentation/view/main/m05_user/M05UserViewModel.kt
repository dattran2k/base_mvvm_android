package com.base.presentation.view.main.m05_user

import com.base.data.respository.DemoRepository
import com.base.data.respository.DemoRepositoryIml
import com.base.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class M05UserViewModel @Inject constructor(val demoRepository: DemoRepository) : BaseViewModel() {
}