package com.bezzo.football2.features.nextEvent

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

class NextEventPresenter<V : NextEventContracts.View> @Inject
constructor(apiHelper: ApiHelper, sessionHelper: SessionHelper, localHelper: LocalStorageHelper,
            schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(apiHelper, sessionHelper, localHelper, schedulerProvider, compositeDisposable),
        NextEventContracts.Presenter<V> {

    override fun getEvents(leagueId: String) {
        view?.showProgressBar()

        apiHelper.sportsRepo.getNextEvent(leagueId)
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