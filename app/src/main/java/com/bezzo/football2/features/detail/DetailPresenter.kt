package com.bezzo.football2.features.detail

import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.local.LocalStorageHelper
import com.bezzo.core.data.model.Team
import com.bezzo.core.data.model.TeamResponse
import com.bezzo.core.data.network.ApiHelper
import com.bezzo.core.data.network.ResponseOkHttp
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Response
import javax.inject.Inject

class DetailPresenter<V : DetailContracts.View> @Inject
constructor(apiHelper: ApiHelper, sessionHelper: SessionHelper, localHelper: LocalStorageHelper,
            schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(apiHelper, sessionHelper, localHelper, schedulerProvider, compositeDisposable),
        DetailContracts.Presenter<V> {

    override fun getHomeBadge(idTeam : String?, teamName: String?) {
        if (idTeam != null && teamName != null)
        apiHelper.sportsRepo.getTeam(teamName).getAsOkHttpResponseAndObject(TeamResponse::class.java,
                object : ResponseOkHttp<TeamResponse>(200){
                    override fun onSuccess(response: Response, model: TeamResponse) {
                        for (value in model.teams){
                            if (value.idTeam == idTeam){
                                view?.homeTeam(value)
                                break
                            }
                        }
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

    override fun getAway(idTeam : String?, teamName: String?) {
        if (idTeam != null && teamName != null)
        apiHelper.sportsRepo.getTeam(teamName).getAsOkHttpResponseAndObject(TeamResponse::class.java,
                object : ResponseOkHttp<TeamResponse>(200){
                    override fun onSuccess(response: Response, model: TeamResponse) {
                        for (value in model.teams){
                            if (value.idTeam == idTeam){
                                view?.awayTeam(value)
                                break
                            }
                        }
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