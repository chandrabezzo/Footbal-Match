package com.bezzo.football2.features.main

import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.CoroutineContextProvider
import com.google.gson.Gson


/**
 * Created by bezzo on 24/01/18.
 * if you use kotlin, when send to view you must add "?" for null check pointer
 * but if you use java, when send to view you must add if(!isViewAttached) return;
 * before you send data to view
 */

class MainPresenter(private val view : MainContracts.View,
                    private val apiRepository: ApiRepository,
                    private val gson : Gson,
                    private val contextPool : CoroutineContextProvider = CoroutineContextProvider())