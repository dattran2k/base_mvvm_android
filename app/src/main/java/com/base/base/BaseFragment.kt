package com.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.base.helper.InternetUtil
import kotlinx.coroutines.launch
import timber.log.Timber


abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T) :
    Fragment() {
    protected var isConnected: Boolean = false
    var _binding: T? = null
    protected val binding get() = _binding!!
    protected var isReady = _binding != null
    var TAG: String = this.javaClass.simpleName;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("$TAG onViewCreated")
        InternetUtil.observe(viewLifecycleOwner) {
            it?.let {
                isConnected = it
            }
        }
        initView()
        initObserver()
        if (isInitGetData())
            getData()
    }

    open fun isInitGetData(): Boolean = true


    /**
     * run after onViewCreated
     */
    abstract fun initView()

    /**
     * obServer state inside viewmodel
     */
    private fun initObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                initObserverCreated()
            }
        }

    }
    open suspend fun initObserverCreated(){

    }
    /**
     * Usually use for get your screen data
     * Helpful when refresh new data
     */
    abstract fun getData()
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d(" $TAG onDestroyView")
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Timber.d(" $TAG onDestroy")
    }
}