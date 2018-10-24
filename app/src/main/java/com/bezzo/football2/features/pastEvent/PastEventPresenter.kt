package com.bezzo.football2.features.pastEvent

import com.androidnetworking.error.ANError
import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.local.LocalStorageHelper
import com.bezzo.core.data.model.EventResponse
import com.bezzo.core.data.network.ApiHelper
import com.bezzo.core.data.network.ResponseOkHttp
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Response
import javax.inject.Inject

class PastEventPresenter<V : PastEventContracts.View> @Inject
constructor(apiHelper: ApiHelper, sessionHelper: SessionHelper, localHelper: LocalStorageHelper,
            schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(apiHelper, sessionHelper, localHelper, schedulerProvider, compositeDisposable),
        PastEventContracts.Presenter<V> {

    override fun getEvents(leagueId: String) {
        view?.showProgressBar()

        apiHelper.sportsRepo.getPastEvent(leagueId)
                .getAsOkHttpResponseAndObject(EventResponse::class.java, object
                    : ResponseOkHttp<EventResponse>(200) {
                    override fun onSuccess(response: Response, model: EventResponse) {
                        view?.showEvents(model.events)
                    }

                    override fun onUnauthorized() {
                        logout()
                    }

                    override fun onFailed(response: Response) {
                        logging(response.message())
                    }

                    override fun onHasError(error: ANError) {
                        handleApiError(error)
                    }

                })
    }
}