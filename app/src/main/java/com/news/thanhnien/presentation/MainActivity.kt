package com.news.thanhnien.presentation

import android.os.Bundle
import com.news.thanhnien.R
import com.news.thanhnien.base.BaseActivity
import com.news.thanhnien.databinding.M00ActivityMainBinding
import com.news.thanhnien.helper.DialogSnackBarUtils
import com.news.thanhnien.helper.NavigationManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: M00ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = M00ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDestinationChange()
        DialogSnackBarUtils.init(this)
        NavigationManager.getInstance().init(this,supportFragmentManager, R.id.fragment_container)
    }

    private fun initDestinationChange() {
    }

}