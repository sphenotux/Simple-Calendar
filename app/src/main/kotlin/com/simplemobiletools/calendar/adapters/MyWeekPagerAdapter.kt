package com.simplemobiletools.calendar.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import com.simplemobiletools.calendar.fragments.WeekFragment
import com.simplemobiletools.calendar.helpers.WEEK_START_TIMESTAMP

class MyWeekPagerAdapter(fm: FragmentManager, val mWeekTimestamps: List<Int>, val mListener: WeekFragment.WeekScrollListener) : FragmentStatePagerAdapter(fm) {
    private val mFragments = SparseArray<WeekFragment>()

    override fun getCount() = mWeekTimestamps.size

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        val weekTimestamp = mWeekTimestamps[position]
        bundle.putInt(WEEK_START_TIMESTAMP, weekTimestamp)

        val fragment = WeekFragment()
        fragment.arguments = bundle
        fragment.setListener(mListener)

        mFragments.put(position, fragment)
        return fragment
    }

    fun updateScrollY(pos: Int, y: Int) {
        (-1..1).map { mFragments[pos + it] }
                .forEach { it?.updateScrollY(y) }
    }
}
