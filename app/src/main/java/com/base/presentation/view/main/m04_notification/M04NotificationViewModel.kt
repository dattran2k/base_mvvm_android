package com.base.presentation.view.main.m04_notification

import com.base.model.network.DemoRepository
import com.base.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class M04NotificationViewModel @Inject constructor(private val notifyRepo: DemoRepository) : BaseViewModel() {}