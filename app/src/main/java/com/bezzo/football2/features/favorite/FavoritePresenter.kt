package com.bezzo.football2.features.favorite

import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson

class FavoritePresenter(private val view : FavoriteView,
                        private val apiRepository: ApiRepository,
                        private val gson : Gson,
                        private val contextPool: CoroutineContextProvider = CoroutineContextProvider())  {
}