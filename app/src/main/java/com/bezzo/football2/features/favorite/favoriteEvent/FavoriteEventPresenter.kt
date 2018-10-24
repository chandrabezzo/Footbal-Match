package com.bezzo.football2.features.favorite.favoriteEvent

import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson

class FavoriteEventPresenter(private val view : FavoriteEventContracts.View,
                             private val apiRepository: ApiRepository,
                             private val gson : Gson,
                             private val contextPool : CoroutineContextProvider = CoroutineContextProvider())