package com.bezzo.football2.adapter.viewPager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.bezzo.football2.R
import com.bezzo.football2.features.match.nextEvent.NextEventFragment
import com.bezzo.football2.features.match.pastEvent.PastEventFragment

class MatchVPAdapter(val context: Context, fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    val PAGE_COUNT = 2
    var tabTitles : Array<String> = arrayOf(context.getString(R.string.next),
            context.getString(R.string.last))

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position) {
            0 -> {
                fragment = NextEventFragment()
            }
            1 -> {
                fragment = PastEventFragment()
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