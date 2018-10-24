package com.bezzo.football2.features.playerDetail

import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson

class PlayerDetailPresenter(private val view : PlayerDetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val contextPool : CoroutineContextProvider = CoroutineContextProvider()) {
}