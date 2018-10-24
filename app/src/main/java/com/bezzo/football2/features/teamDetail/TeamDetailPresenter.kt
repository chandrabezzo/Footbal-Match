package com.bezzo.football2.features.teamDetail

import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.CoroutineContextProvider
import com.bezzo.football2.features.playerDetail.PlayerDetailView
import com.google.gson.Gson

class TeamDetailPresenter(private val view : TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val contextPool : CoroutineContextProvider = CoroutineContextProvider()) {
}