package com.bezzo.football2.features.nextEvent

import com.bezzo.core.data.model.EventResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextEventPresenter(private val view : NextEventContracts.View,
                         private val apiRepository: ApiRepository,
                         private val gson : Gson,
                         private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEvents(leagueId: String) {
        view.showProgressBar()

        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.getNextEvent(leagueId)),
                        EventResponse::class.java)
            }
            view.showEvents(data.await().events)
            view.hideProgressBar()
        }
    }
}