package com.bezzo.football2.features.favoriteEvent

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.model.Event
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.recyclerView.EventRVAdapter
import com.bezzo.football2.features.detail.DetailActivity
import com.bezzo.football2.features.pastEvent.PastEventFragment
import com.bezzo.football2.ui.EventUI
import com.bezzo.football2.utils.database
import com.bezzo.football2.utils.hide
import com.bezzo.football2.utils.visible
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import javax.inject.Inject

class FavoriteEventFragment : BaseFragment(), FavoriteEventContracts.View {

    @Inject
    lateinit var presenter : FavoriteEventPresenter<FavoriteEventContracts.View>

    private var events : MutableList<Event> = mutableListOf()
    private lateinit var eventAdapter : EventRVAdapter
    lateinit var progressBar : ProgressBar
    lateinit var swipeRefresh : SwipeRefreshLayout
    lateinit var rvEvent : RecyclerView

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): View = EventUI<FavoriteEventFragment>().createView(AnkoContext
            .create(ctx, this))

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBar = find(R.id.pb_event)
        swipeRefresh = find(R.id.swipe_event)
        rvEvent = find(R.id.rv_event)

        eventAdapter = EventRVAdapter(ctx, events){
            var data = Bundle()
            data.putString(ApiConstans.DATA, presenter.gson.toJson(it))

            goToActivity(DetailActivity::class.java, data, false)
        }
        rvEvent.adapter = eventAdapter

        showEvents()

        swipeRefresh.setOnRefreshListener {
            showEvents()
        }
    }

    override fun showEvents() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            hideProgressBar()
            val result = select(Event.TABLE_EVENT)
            val favorites = result.parseList(classParser<Event>())
            events.clear()
            events.addAll(favorites)
            eventAdapter.notifyDataSetChanged()
        }

    }

    override fun showProgressBar() {
        progressBar.visible()
    }

    override fun hideProgressBar() {
        progressBar.hide()
    }
}
