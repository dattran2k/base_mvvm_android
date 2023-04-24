package com.base.presentation

import android.os.Bundle
import com.base.R
import com.base.base.BaseActivity
import com.base.databinding.M00ActivityMainBinding
import com.base.helper.DialogSnackBarUtils
import com.base.helper.NavigationManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: M00ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = M00ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DialogSnackBarUtils.init(this)
        NavigationManager.getInstance().init(this,supportFragmentManager, R.id.fragment_container)
    }



}