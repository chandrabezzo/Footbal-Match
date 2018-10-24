package com.bezzo.football2.features.match

import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson

class MatchPresenter(private val view : MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val contextPool : CoroutineContextProvider = CoroutineContextProvider()) {
}