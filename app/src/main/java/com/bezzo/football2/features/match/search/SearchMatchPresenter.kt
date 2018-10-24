package com.bezzo.football2.features.match.search

import com.bezzo.core.data.model.SearchEventResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(private val view : SearchMatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val contextPool : CoroutineContextProvider = CoroutineContextProvider()) {

    fun searchMatch(query : String){
        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(SportsRepo.searchMatch(query)),
                        SearchEventResponse::class.java)
            }

            view.showMatch(data.await().events)
        }
    }
}