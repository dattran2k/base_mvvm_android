package com.news.thanhnien.helper

import android.annotation.SuppressLint
import android.app.Activity
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.news.thanhnien.R
import com.news.thanhnien.presentation.view.main.M00MainFragment
import com.news.thanhnien.presentation.view.main.m01_home.M01HomeFragment
import timber.log.Timber

class NavigationManager() :
    FragmentManager.OnBackStackChangedListener {
    protected lateinit var mActivity: Activity
    protected lateinit var mFragmentManager: FragmentManager
    private var mContentId: Int? = null
    private var lastFm = ""

    companion object {
        fun getInstance(): NavigationManager = NavigationManagerHolder.navigationManagerHolder
    }

    private object NavigationManagerHolder {
        @SuppressLint("StaticFieldLeak")
        val navigationManagerHolder = NavigationManager()
    }

    fun init(activity: Activity, fragmentManager: FragmentManager, @IdRes contentId: Int) {
        mActivity = activity
        mFragmentManager = fragmentManager
        mContentId = contentId
        mFragmentManager.addOnBackStackChangedListener(this)
    }


    /**
     * flag mark the Navigation is created on Fragment class
     */
    fun isBackStackEmpty() = mFragmentManager.backStackEntryCount == 0
    fun isRoot() = mFragmentManager.backStackEntryCount <= 1

    fun popBackStack() {
        mActivity.onBackPressed()
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
            mFragmentManager.commit {
                if (enter > 0 || exit > 0 || popEnter > 0 || popExit > 0) {
                    setCustomAnimations(enter, exit, popEnter, popExit)
                }
                mContentId?.let {
                    if (isReplace)
                        replace(it, fragment)
                    else
                        add(it, fragment)

                }
                addToBackStack((2147483646.0 * Math.random()).toInt().toString())
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }
    fun openFragment(
        fragment: Fragment,
        isReplace: Boolean = false,
    ) {
        openFragment(
            fragment, isReplace,
            R.anim.slide_in_left,
            R.anim.opacity_1_to_0,
            0,
            R.anim.slide_out_right
        )
    }
    fun openFragmentBottomUp(
        fragment: Fragment,
        isReplace: Boolean = false,
    ) {
        openFragment(
            fragment, isReplace,
            R.anim.slide_in_up,
            R.anim.opacity_1_to_0,
           0,
            R.anim.slide_out_down
        )
    }
    fun getCurrentFragment(): Fragment? {
        return try {
            mContentId?.let { mFragmentManager.findFragmentById(it) }
        } catch (e: Exception) {
            e.stackTrace
            null
        }
    }

    override fun onBackStackChanged() {
        if (getCurrentFragment() == null) return
        val fragment: Fragment? = getCurrentFragment()
        Timber.e(fragment.toString())

    }

}