package com.bezzo.football2.features.favorite.favoriteTeam

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.model.Team
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.recyclerView.TeamRVAdapter
import com.bezzo.football2.features.teamDetail.TeamDetailActivity
import com.bezzo.football2.utils.database
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamFragment : BaseFragment(), FavoriteTeamView {

    lateinit var presenter : FavoriteTeamPresenter

    val request = ApiRepository()
    val gson = Gson()

    lateinit var adapter : TeamRVAdapter
    var list = ArrayList<Team>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter = FavoriteTeamPresenter(this, request, gson)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_favorite_team
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            adapter = TeamRVAdapter(it, list)
            rv_team.layoutManager = LinearLayoutManager(it)
            rv_team.adapter = adapter

            adapter.setOnItemClick(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    val data = Bundle()
                    data.putString(ApiConstans.DATA, gson.toJson(list[position]))

                    goToActivityForResult(TeamDetailActivity::class.java, data, 0)
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }

            })

            showTeams()
        }

        sr_favorite_team.setOnRefreshListener {
            showTeams()
        }
    }

    override fun showTeams() {
        context?.database?.use {
            sr_favorite_team.isRefreshing = false
            val result = select(Team.TABLE_TEAM)
            val favorites = result.parseList(classParser<Team>())
            list.clear()
            list.addAll(favorites)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        showTeams()
    }
}
