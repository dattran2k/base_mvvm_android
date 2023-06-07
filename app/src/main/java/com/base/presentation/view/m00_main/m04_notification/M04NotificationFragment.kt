package com.base.presentation.view.m00_main.m04_notification

import androidx.fragment.app.viewModels
import com.base.presentation.BaseFragment
import com.base.databinding.M04FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M04NotificationFragment : BaseFragment<M04FragmentNotificationBinding>(M04FragmentNotificationBinding::inflate) {
    private val viewModel: M04NotificationViewModel by viewModels()

    override fun initView() {

    }

   

}