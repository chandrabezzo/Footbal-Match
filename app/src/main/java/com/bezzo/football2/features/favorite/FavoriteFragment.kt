package com.bezzo.football2.features.favorite

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.football2.R
import com.bezzo.football2.adapter.viewPager.FavoriteVPAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment(), FavoriteView {

    lateinit var presenter: FavoritePresenter

    val request = ApiRepository()
    val gson = Gson()

    lateinit var adapter : FavoriteVPAdapter

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter = FavoritePresenter(this, request, gson)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            fragmentManager?.let { fm ->
                adapter = FavoriteVPAdapter(it, fm)
                vp_favorite.adapter = adapter
                tl_favorite.setupWithViewPager(vp_favorite)
                vp_favorite.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_favorite))

                tl_favorite.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                    override fun onTabReselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.let {
                            vp_favorite.currentItem = it.position
                        }
                    }

                })
            }
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_favorite
    }
}
