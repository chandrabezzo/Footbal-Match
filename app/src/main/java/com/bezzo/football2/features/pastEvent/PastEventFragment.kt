package com.bezzo.football2.features.pastEvent

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.recyclerView.EventRVAdapter
import com.bezzo.football2.features.detail.DetailActivity
import com.bezzo.football2.ui.FavoriteEventUI
import com.bezzo.football2.ui.PastEventUI
import com.bezzo.football2.utils.hide
import com.bezzo.football2.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find

class PastEventFragment : BaseFragment(), PastEventContracts.View {

    lateinit var presenter : PastEventPresenter

    private var events : MutableList<Event> = mutableListOf()
    private lateinit var eventAdapter : EventRVAdapter
    lateinit var progressBar : ProgressBar
    lateinit var swipeRefresh : SwipeRefreshLayout
    lateinit var rvEvent : RecyclerView
    val requests = ApiRepository()
    val gson = Gson()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter = PastEventPresenter(this, requests, gson)
    }

    override fun setLayout(): View = PastEventUI<PastEventFragment>().createView(AnkoContext
            .create(ctx, this))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = find(R.id.pb_prev_event)
        swipeRefresh = find(R.id.swipe_prev_event)
        rvEvent = find(R.id.rv_prev_event)

        eventAdapter = EventRVAdapter(events){
            var data = Bundle()
            data.putString(ApiConstans.DATA, gson.toJson(it))

            goToActivity(DetailActivity::class.java, data, false)
        }
        rvEvent.adapter = eventAdapter

        presenter.getEvents("4328")

        swipeRefresh.setOnRefreshListener {
            presenter.getEvents("4328")
        }
    }

    override fun showEvents(values: List<Event>) {
        swipeRefresh.isRefreshing = false
        hideProgressBar()

        events.clear()
        events.addAll(values)
        eventAdapter.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        progressBar.visible()
    }

    override fun hideProgressBar() {
        progressBar.hide()
    }
}
