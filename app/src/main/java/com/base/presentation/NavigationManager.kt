package com.base.presentation

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener
import androidx.fragment.app.commit
import com.base.R
import timber.log.Timber

class NavigationManager {
    private lateinit var mFragmentManager: FragmentManager
    private var mContentId: Int? = null
    private var navigateAble = true
    private val handlerNavigate = Handler(Looper.getMainLooper())
    private val backHandler = Handler(Looper.getMainLooper())
    private var isBack = false


    companion object {
        fun getInstance(): NavigationManager = NavigationManagerHolder.navigationManagerHolder
    }

    private object NavigationManagerHolder {
        val navigationManagerHolder = NavigationManager()
    }

    fun init(
        activity: AppCompatActivity,
        @IdRes contentId: Int,
        onBackStackChangedListener: OnBackStackChangedListener
    ) {
        mFragmentManager = activity.supportFragmentManager
        mContentId = contentId
        mFragmentManager.addOnBackStackChangedListener(onBackStackChangedListener)
        activity.onBackPressedDispatcher.addCallback(activity, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Timber.e("onBackPressed ${mFragmentManager.backStackEntryCount}")
                if (isBackStackEmpty()) {
                    if (!isBack) {
                        Toast.makeText(activity, activity.getString(R.string.text_click_close_app), Toast.LENGTH_LONG).show()
                        isBack = true
                        backHandler.postDelayed({
                            isBack = false
                        }, 2000)
                    } else {
                        activity.finish()
                    }
                } else {
                  popBackStack()
                }
            }
        })
    }

    fun isBackStackEmpty() = mFragmentManager.backStackEntryCount == 0

    fun popBackStack() {
        mFragmentManager.popBackStack()
    }

    private inline fun <reified T : Fragment> getFragmentInBackStack(mFragmentManager: FragmentManager): T? {
        mFragmentManager.fragments.forEach {
            val findMainFragment = it
            if (findMainFragment is T) {
                return findMainFragment
            }
        }
        return null
    }

    fun openFragment(
        fragment: Fragment,
        isReplace: Boolean = false,
        @AnimRes enter: Int,
        @AnimRes exit: Int,
        @AnimRes popEnter: Int,
        @AnimRes popExit: Int
    ) {
        try {
            if (!navigateAble)
                return
            mFragmentManager.commit {
                if (enter != 0 || exit != 0 || popEnter != 0 || popExit != 0) {
                    setCustomAnimations(enter, exit, popEnter, popExit)
                }
                mContentId?.let {
                    if (isReplace)
                        replace(it, fragment)
                    else
                        add(it, fragment, fragment::class.simpleName)

                }
                addToBackStack((2147483646.0 * Math.random()).toInt().toString())
                navigateAble = false
                handlerNavigate.postDelayed({
                    navigateAble = true
                }, 1000)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openFragment(fragment: Fragment, isReplace: Boolean = false) {
        openFragment(
            fragment,
            isReplace,
            R.anim.slide_in_left,
            R.anim.opacity_1_to_0,
            0,
            R.anim.slide_out_right
        )
    }

    fun openFragmentBottomUp(fragment: Fragment, isReplace: Boolean = false) {
        openFragment(
            fragment,
            isReplace,
            R.anim.slide_in_up,
            R.anim.opacity_1_to_0,
            0,
            R.anim.slide_out_down
        )
    }

    fun getCurrentFragment(): Fragment? {
        return try {
            mContentId?.let {
                mFragmentManager.findFragmentById(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun release(){
        handlerNavigate.removeCallbacksAndMessages(null)
        backHandler.removeCallbacksAndMessages(null)
    }
}