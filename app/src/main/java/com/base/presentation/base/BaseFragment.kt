package com.base.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.base.R
import kotlinx.coroutines.launch
import timber.log.Timber


abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T) :
    Fragment() {

    protected var _binding: T? = null
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
    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                initObserverCreated()
            }
        }
    }

    open suspend fun initObserverCreated() {
    }

    protected fun navigate(
        action: NavDirections,
        animNavigate: AnimNavigate = AnimNavigate.LEFT_RIGHT,
        isReplace: Boolean = false
    ) {
        findNavController().navigate(
            action,
            getNavOptions(animNavigate, isReplace)
        )
    }

    protected fun navigate(
        id: Int,
        arg: Bundle? = null,
        animNavigate: AnimNavigate = AnimNavigate.LEFT_RIGHT

    ) {
        findNavController().navigate(
            id,
            arg,
            getNavOptions(animNavigate)
        )
    }

    private fun getNavOptions(
        animNavigate: AnimNavigate,
        isReplace: Boolean = false
    ) =
        NavOptions.Builder().apply {
            when (animNavigate) {
                AnimNavigate.LEFT_RIGHT -> {
                    setEnterAnim(R.anim.slide_in_left)
                    setExitAnim(R.anim.opacity_1_to_0)
                    setPopExitAnim(R.anim.slide_out_right)
                }

                AnimNavigate.UP_DOWN -> {
                    setEnterAnim(R.anim.slide_in_up)
                    setExitAnim(R.anim.opacity_1_to_0)
                    setPopExitAnim(R.anim.slide_out_down)
                }
                AnimNavigate.FADE -> {
                    setEnterAnim(R.anim.opacity_0_to_1)
                    setExitAnim(R.anim.opacity_1_to_0)
                    setPopExitAnim(R.anim.opacity_1_to_0)
                }
                AnimNavigate.NONE -> {
                }
            }
            if (isReplace)
                findNavController().currentDestination?.id?.let {
                    setPopUpTo(it, true)
                }

        }.build()
    /**
     * popBackStack
     */
    protected fun pop() {
        findNavController().popBackStack()
    }

    /**
     * Pop utils --->
     */
    protected fun popTo(@IdRes destination: Int) {
        findNavController().popBackStack(destination, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d(" $TAG onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Timber.d(" $TAG onDestroy")
    }

    enum class AnimNavigate {
        LEFT_RIGHT,
        UP_DOWN,
        FADE,NONE
    }
}