package vn.vtvnews.presentation

import android.os.Bundle
import vn.vtvnews.R
import vn.vtvnews.base.BaseActivity
import vn.vtvnews.databinding.M00ActivityMainBinding
import vn.vtvnews.helper.DialogSnackBarUtils
import vn.vtvnews.helper.NavigationManager
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