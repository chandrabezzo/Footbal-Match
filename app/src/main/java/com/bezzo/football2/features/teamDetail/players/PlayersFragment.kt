package com.bezzo.football2.features.teamDetail.players

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.data.model.PlayerResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.recyclerView.PlayerRVAdapter
import com.bezzo.football2.features.playerDetail.PlayerDetailActivity
import com.bezzo.football2.features.teamDetail.overview.OverviewTeamFragment
import com.bezzo.football2.utils.hide
import com.bezzo.football2.utils.show
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_players.*

class PlayersFragment : BaseFragment(), PlayersView {

    lateinit var presenter : PlayersPresenter

    val request = ApiRepository()
    val gson = Gson()

    lateinit var adapter : PlayerRVAdapter
    var list = ArrayList<PlayerResponse.Player>()

    companion object {
        fun newInstance(teamId : String?) : Fragment {
            var args = Bundle()
            args.putString(ApiConstans.TEAM_ID, teamId)

            var fragment = PlayersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter = PlayersPresenter(this, request, gson)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            adapter = PlayerRVAdapter(it, list)
            rv_players.layoutManager = LinearLayoutManager(it)
            rv_players.adapter = adapter
            dataReceived?.getString(ApiConstans.TEAM_ID)?.let { teamId -> presenter.getPlayers(teamId) }

            adapter.setOnItemClick(object : OnItemClickListener{
                override fun onItemClick(itemView: View, position: Int) {
                    val data = Bundle()
                    data.putString(ApiConstans.DATA, gson.toJson(list[position]))

                    goToActivity(PlayerDetailActivity::class.java, data, false)
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }

            })
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_players
    }

    override fun showLoadMore() {
        pb_players.show()
    }

    override fun hideLoadMore() {
        pb_players.hide()
    }

    override fun showPlayers(values: List<PlayerResponse.Player>) {
        list.clear()
        list.addAll(values)
        adapter.notifyDataSetChanged()
    }
}
