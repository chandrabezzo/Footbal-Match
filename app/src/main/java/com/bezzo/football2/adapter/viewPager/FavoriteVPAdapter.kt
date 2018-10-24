package com.bezzo.football2.adapter.viewPager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.bezzo.football2.R
import com.bezzo.football2.features.favorite.favoriteEvent.FavoriteEventFragment
import com.bezzo.football2.features.favorite.favoriteTeam.FavoriteTeamFragment

class FavoriteVPAdapter(val context: Context, fragmentManager : FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    val PAGE_COUNT = 2
    var tabTitles : Array<String> = arrayOf(context.getString(R.string.matches),
            context.getString(R.string.teams))

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> {
                fragment = FavoriteEventFragment()
            }
            1 -> {
                fragment = FavoriteTeamFragment()
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