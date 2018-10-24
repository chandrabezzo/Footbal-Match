package com.bezzo.football2.features.team

import com.bezzo.core.data.model.LeagueResponse
import com.bezzo.core.data.model.TeamResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(private val view : TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val contextPool : CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLeagues(){
        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.getLeague()),
                        LeagueResponse::class.java)
            }
            data.await().leagues?.let { view.showLeagues(it) }
        }
    }

    fun getTeams(leagueId : String){
        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.getTeams(leagueId)),
                        TeamResponse::class.java)
            }
            data.await().teams.let { view.showTeams(it) }
        }
    }

    fun searchTeams(query : String){
        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.searchTeams(query)),
                        TeamResponse::class.java)
            }
            data.await().teams.let { view.showTeams(it) }
        }
    }
}