package com.bezzo.football2.features.match.pastEvent

import com.bezzo.core.data.model.EventResponse
import com.bezzo.core.data.model.LeagueResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PastEventPresenter(private val view : PastEventView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val contextPool : CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEvents(leagueId: String) {
        view.showProgressBar()

        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.getPastEvent(leagueId)),
                        EventResponse::class.java)
            }

            view.showEvents(data.await().events)
            view.hideProgressBar()
        }
    }

    fun getLeague(){
        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.getLeague()),
                        LeagueResponse::class.java)
            }
            data.await().leagues?.let { view.showLeagues(it) }
        }
    }
}