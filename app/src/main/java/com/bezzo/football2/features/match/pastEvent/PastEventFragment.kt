package com.bezzo.football2.features.match.pastEvent

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.LeagueResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.recyclerView.EventRVAdapter
import com.bezzo.football2.adapter.spinner.LeagueSPAdapter
import com.bezzo.football2.features.match.detail.DetailActivity
import com.bezzo.football2.utils.hide
import com.bezzo.football2.utils.show
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_past_event.*

class PastEventFragment : BaseFragment(), PastEventView {

    lateinit var presenter : PastEventPresenter

    val request = ApiRepository()
    val gson = Gson()

    lateinit var adapter : EventRVAdapter
    var list = ArrayList<Event>()

    lateinit var leagueAdapter : LeagueSPAdapter
    var listLeague = ArrayList<LeagueResponse.League>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter = PastEventPresenter(this, request, gson)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_past_event
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leagueAdapter = LeagueSPAdapter(listLeague)
        sp_league_past.adapter = leagueAdapter
        presenter.getLeague()

        adapter = EventRVAdapter(list, false)
        val layoutManager = LinearLayoutManager(context)
        rv_past_event.layoutManager = layoutManager
        rv_past_event.adapter = adapter

        sp_league_past.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                listLeague[sp_league_past.selectedItemPosition].idLeague?.let { presenter.getEvents(it) }
            }
        }

        adapter.setOnItemClick(object : OnItemClickListener {
            override fun onItemClick(itemView: View, position: Int) {
                var data = Bundle()
                data.putString(ApiConstans.DATA, gson.toJson(list[position]))

                goToActivity(DetailActivity::class.java, data, false)
            }

            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return true
            }
        })
    }

    override fun showEvents(values: List<Event>) {
        sr_past_event.isRefreshing = false
        hideProgressBar()

        list.clear()
        list.addAll(values)
        adapter.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        pb_past_event.show()
    }

    override fun hideProgressBar() {
        pb_past_event.hide()
    }

    override fun showLeagues(values: List<LeagueResponse.League>) {
        leagueAdapter.update(values)
    }
}
