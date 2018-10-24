package com.bezzo.football2.features.teamDetail.players

import com.bezzo.core.data.model.EventResponse
import com.bezzo.core.data.model.PlayerResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayersPresenter(private val view : PlayersView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson,
                       private val contextPool : CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayers(idTeam: String) {
        view.showLoadMore()

        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.getPlayers(idTeam)),
                        PlayerResponse::class.java)
            }

            data.await().player?.let { view.showPlayers(it) }
            view.hideLoadMore()
        }
    }
}