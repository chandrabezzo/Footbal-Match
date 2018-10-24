package com.bezzo.football2.features.favorite.favoriteEvent

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.recyclerView.EventRVAdapter
import com.bezzo.football2.features.match.detail.DetailActivity
import com.bezzo.football2.utils.database
import com.bezzo.football2.utils.hide
import com.bezzo.football2.utils.show
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteEventFragment : BaseFragment(), FavoriteEventContracts.View {

    lateinit var presenter : FavoriteEventPresenter

    private lateinit var eventAdapter : EventRVAdapter
    var events = ArrayList<Event>()
    val request = ApiRepository()
    val gson = Gson()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter = FavoriteEventPresenter(this, request, gson)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        eventAdapter = EventRVAdapter(events, false)
        rv_favorite_event.layoutManager = LinearLayoutManager(context)
        rv_favorite_event.adapter = eventAdapter
        showEvents()

        sr_favorite_event.setOnRefreshListener {
            showEvents()
        }

        eventAdapter.setOnItemClick(object : OnItemClickListener{
            override fun onItemClick(itemView: View, position: Int) {
                val data = Bundle()
                data.putString(ApiConstans.DATA, gson.toJson(events[position]))

                goToActivityForResult(DetailActivity::class.java, data, 0)
            }

            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return true
            }

        })
    }

    override fun showEvents() {
        context?.database?.use {
            sr_favorite_event.isRefreshing = false
            hideProgressBar()
            val result = select(Event.TABLE_EVENT)
            val favorites = result.parseList(classParser<Event>())
            events.clear()
            events.addAll(favorites)
            eventAdapter.notifyDataSetChanged()
        }

    }

    override fun showProgressBar() {
        pb_favorite_event.show()
    }

    override fun hideProgressBar() {
        pb_favorite_event.hide()
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_favorite_event
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        showEvents()
    }
}
