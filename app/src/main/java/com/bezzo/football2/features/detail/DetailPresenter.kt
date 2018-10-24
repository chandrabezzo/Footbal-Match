package com.bezzo.football2.features.detail

import com.bezzo.core.data.model.TeamResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val view : DetailContracts.View,
                      private val apiRepository: ApiRepository,
                      private val gson : Gson,
                      private val contextPool : CoroutineContextProvider = CoroutineContextProvider()) {

    fun getHome(idTeam : String?, teamName: String?) {
        if (idTeam != null && teamName != null)
        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.getTeam(teamName)),
                        TeamResponse::class.java)
            }
            for (value in data.await().teams){
                if (value.idTeam == idTeam){
                    view.homeTeam(value)
                    break
                }
            }
        }

    }

    fun getAway(idTeam : String?, teamName: String?) {
        if (idTeam != null && teamName != null)
            async(contextPool.main){
                val data = bg {
                    gson.fromJson(apiRepository.doRequest(SportsRepo.getTeam(teamName)),
                            TeamResponse::class.java)
                }
                for (value in data.await().teams){
                    if (value.idTeam == idTeam){
                        view.awayTeam(value)
                        break
                    }
                }
            }
    }
}