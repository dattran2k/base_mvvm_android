package com.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber


abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T) :
    Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected var isReady = _binding != null
    protected var navigationManager = NavigationManager.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        initView()
        initObserver()
    }

    /**
     * run after onViewCreated
     */
    abstract fun initView()

    /**
     * obServer state inside viewmodel
     */
    abstract fun initObserver()


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Timber.d("onDestroy")
    }

}

