package com.bezzo.football2.features.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.football2.R
import com.bezzo.football2.adapter.viewPager.MatchVPAdapter
import com.bezzo.football2.features.match.search.SearchMatchActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : BaseFragment(), MatchView {

    lateinit var presenter : MatchPresenter

    val request = ApiRepository()
    val gson = Gson()

    lateinit var adapter : MatchVPAdapter

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter = MatchPresenter(this, request, gson)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            fragmentManager?.let { fm ->
                adapter = MatchVPAdapter(it, fm)
                vp_match.adapter = adapter
                tl_match.setupWithViewPager(vp_match)
                vp_match.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_match))

                tl_match.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                    override fun onTabReselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.let {
                            vp_match.currentItem = it.position
                        }
                    }

                })
            }
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_match
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.nav_search -> {
                goToActivity(SearchMatchActivity::class.java, null, false)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
