package com.base.presentation.view.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter<T : Fragment>(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {
    private val listFragment: ArrayList<T> = ArrayList()
    override fun getItemCount() = listFragment.size

    override fun createFragment(position: Int): T {
        // Return a NEW fragment instance in createFragment(int)
        return listFragment[position]
    }

    fun setListFragment(fragments: List<T>) {

        listFragment.clear()
        listFragment.addAll(fragments)
        notifyDataSetChanged()
    }

    fun addFragment(fragments: List<T>) {
        val prevSize = listFragment.size
        listFragment.addAll(fragments)
        notifyItemRangeInserted(prevSize, fragments.size)
    }

    fun getFragment(index: Int): T? {
        return listFragment.getOrNull(index)
    }

    fun getListFragment() = listFragment
}