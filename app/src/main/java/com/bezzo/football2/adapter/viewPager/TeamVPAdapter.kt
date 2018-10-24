package com.bezzo.football2.adapter.viewPager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.bezzo.football2.R
import com.bezzo.football2.features.teamDetail.overview.OverviewTeamFragment
import com.bezzo.football2.features.teamDetail.players.PlayersFragment

class TeamVPAdapter(val context: Context, val fragmentManager: FragmentManager, val overview : String?,
                    val teamId : String?)
    : FragmentStatePagerAdapter(fragmentManager) {

    val PAGE_COUNT = 2
    val tabTitles : Array<String> = arrayOf(context.getString(R.string.overview),
            context.getString(R.string.players))

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        fragment = if (position == 0){
            OverviewTeamFragment.newInstance(overview)
        }
        else {
            PlayersFragment.newInstance(teamId)
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