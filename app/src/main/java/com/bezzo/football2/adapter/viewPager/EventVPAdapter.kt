package com.bezzo.football2.adapter.viewPager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.bezzo.football2.R
import com.bezzo.football2.features.nextEvent.NextEventFragment
import com.bezzo.football2.features.pastEvent.PastEventFragment

class EventVPAdapter constructor(val context: Context, val fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    val PAGE_COUNT = 2
    val tabTitles = arrayOf(context.getString(R.string.prev_match), context.getString(R.string.next_match))
    val tabIcons = intArrayOf(R.drawable.ic_history_blue, R.drawable.ic_event_blue)

    override fun getItem(position: Int): Fragment {
        lateinit var fragment : Fragment

        when(position){
            0 -> {
                fragment = PastEventFragment()
            }
            1 -> {
                fragment = NextEventFragment()
            }
        }

        return fragment
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}