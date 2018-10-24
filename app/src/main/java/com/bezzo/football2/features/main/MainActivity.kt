package com.bezzo.football2.features.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.football2.R
import com.bezzo.football2.adapter.viewPager.MatchVPAdapter
import com.bezzo.football2.features.favorite.FavoriteFragment
import com.bezzo.football2.features.match.MatchFragment
import com.bezzo.football2.features.team.TeamFragment
import com.google.gson.Gson
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_match.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContracts.View, HasSupportFragmentInjector {

    lateinit var presenter : MainPresenter

    val request = ApiRepository()
    val gson = Gson()

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, request, gson)

        setActionBarTitle(getString(R.string.app_name))

        if (savedInstanceState == null){
            gotoFragment(R.id.fl_main, null, MatchFragment::class.java)
        }

        bnv_main.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_match -> {
                    gotoFragment(R.id.fl_main, null, MatchFragment::class.java)
                    true
                }
                R.id.nav_teams -> {
                    gotoFragment(R.id.fl_main, null, TeamFragment::class.java)
                    true
                }
                R.id.nav_favorites -> {
                    gotoFragment(R.id.fl_main, null, FavoriteFragment::class.java)
                    true
                }
                else -> {
                    gotoFragment(R.id.fl_main, null, MatchFragment::class.java)
                    true
                }
            }
        }
    }
}
