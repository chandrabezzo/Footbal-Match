package com.bezzo.football2.features.pastEvent

import com.androidnetworking.error.ANError
import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.local.LocalStorageHelper
import com.bezzo.core.data.model.EventResponse
import com.bezzo.core.data.network.ApiHelper
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.data.network.ResponseOkHttp
import com.bezzo.core.data.network.SportsRepo
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.CoroutineContextProvider
import com.bezzo.core.util.SchedulerProvider
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.experimental.async
import okhttp3.Response
import org.jetbrains.anko.coroutines.experimental.bg
import javax.inject.Inject

class PastEventPresenter(private val view : PastEventContracts.View,
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
}