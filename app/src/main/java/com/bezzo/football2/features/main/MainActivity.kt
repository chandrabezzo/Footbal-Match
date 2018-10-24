package com.bezzo.football2.features.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.bezzo.core.base.BaseActivity
import com.bezzo.football2.R
import com.bezzo.football2.adapter.viewPager.EventVPAdapter
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

    lateinit var eventAdapter : EventVPAdapter

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)

        setActionBarTitle(getString(R.string.app_name))

        eventAdapter = EventVPAdapter(this, supportFragmentManager)
        vp_event.adapter = eventAdapter
        tl_event.setupWithViewPager(vp_event)
        tl_event.getTabAt(0)?.setIcon(R.drawable.ic_history_blue)
        tl_event.getTabAt(1)?.setIcon(R.drawable.ic_event_blue)

        vp_event.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tl_event){})

        tl_event.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null){
                    vp_event.currentItem = tab.position
                }
            }

        })
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
