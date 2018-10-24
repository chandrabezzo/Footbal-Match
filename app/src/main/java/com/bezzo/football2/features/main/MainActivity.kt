package com.bezzo.football2.features.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.bezzo.core.base.BaseActivity
import com.bezzo.football2.R
import com.bezzo.football2.features.favoriteEvent.FavoriteEventFragment
import com.bezzo.football2.features.nextEvent.NextEventFragment
import com.bezzo.football2.features.pastEvent.PastEventFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContracts.View, HasSupportFragmentInjector {

    @Inject
    lateinit var presenter : MainPresenter<MainContracts.View>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)

        setActionBarTitle(getString(R.string.app_name))

        if (savedInstanceState == null){
            gotoFragment(R.id.fl_main, null, PastEventFragment::class.java)
        }

        bnv_main.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_prev_match -> {
                    gotoFragment(R.id.fl_main, null, PastEventFragment::class.java)
                    true
                }
                R.id.nav_next_match -> {
                    gotoFragment(R.id.fl_main, null, NextEventFragment::class.java)
                    true
                }
                R.id.nav_favorite_match -> {
                    gotoFragment(R.id.fl_main, null, FavoriteEventFragment::class.java)
                    true
                }
                else -> {
                    gotoFragment(R.id.fl_main, null, PastEventFragment::class.java)
                    true
                }
            }
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
