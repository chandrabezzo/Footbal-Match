package com.bezzo.football2.features.team

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.model.LeagueResponse
import com.bezzo.core.data.model.Team
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.recyclerView.TeamRVAdapter
import com.bezzo.football2.adapter.spinner.LeagueSPAdapter
import com.bezzo.football2.features.teamDetail.TeamDetailActivity
import com.bezzo.football2.utils.hide
import com.bezzo.football2.utils.show
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*

class TeamFragment : BaseFragment(), TeamView {

    lateinit var presenter : TeamPresenter

    val request = ApiRepository()
    val gson = Gson()

    lateinit var leagueAdapter : LeagueSPAdapter
    var listLeague = ArrayList<LeagueResponse.League>()

    lateinit var adapter : TeamRVAdapter
    var list = ArrayList<Team>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter = TeamPresenter(this, request, gson)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_team
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        leagueAdapter = LeagueSPAdapter(listLeague)
        sp_league.adapter = leagueAdapter
        presenter.getLeagues()

        context?.let {
            adapter = TeamRVAdapter(it, list)
            rv_team.layoutManager = LinearLayoutManager(it)
            rv_team.adapter = adapter

            sp_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    showLoadMore()
                    listLeague[sp_league.selectedItemPosition].idLeague?.let { presenter.getTeams(it) }
                }
            }

            adapter.setOnItemClick(object : OnItemClickListener{
                override fun onItemClick(itemView: View, position: Int) {
                    val data = Bundle()
                    data.putString(ApiConstans.DATA, gson.toJson(list[position]))

                    goToActivity(TeamDetailActivity::class.java, data, false)
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }

            })
        }
    }

    override fun showLeagues(values: List<LeagueResponse.League>) {
        leagueAdapter.update(values)
    }

    override fun showLoadMore() {
        pb_team.show()
    }

    override fun hideLoadMore() {
        pb_team.hide()
    }

    override fun showTeams(values: List<Team>) {
        hideLoadMore()

        list.clear()
        list.addAll(values)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_navigation, menu)
        val searchItem = menu?.findItem(R.id.nav_search)

        val searchView: SearchView? = searchItem?.actionView as SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    showLoadMore()
                    presenter.searchTeams(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (!query.isEmpty()) {
                    showLoadMore()
                    presenter.searchTeams(query)
                }
                return false
            }
        })
        searchView?.requestFocus()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
