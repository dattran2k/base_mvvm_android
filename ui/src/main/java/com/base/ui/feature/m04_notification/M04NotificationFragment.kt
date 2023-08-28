package com.base.ui.feature.m04_notification

import androidx.fragment.app.viewModels
import com.base.ui.databinding.M04FragmentNotificationBinding
import com.base.ui.feature.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M04NotificationFragment : BaseFragment<M04FragmentNotificationBinding>(M04FragmentNotificationBinding::inflate) {
    private val viewModel: M04NotificationViewModel by viewModels()

    override fun initView() {

    }

   

}