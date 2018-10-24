package com.bezzo.football2.features.detail

import android.database.sqlite.SQLiteConstraintException
import com.androidnetworking.error.ANError
import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.local.LocalStorageHelper
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.TeamResponse
import com.bezzo.core.data.network.ApiHelper
import com.bezzo.core.data.network.ResponseOkHttp
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProvider
import com.bezzo.football2.utils.database
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Response
import org.jetbrains.anko.db.*
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

    override fun addToFavorite(event: Event) {
        try {
            view?.getContext()?.database?.use {
                insert(Event.TABLE_EVENT,
                        Event.ID_EVENT to event.idEvent,
                        Event.EVENT_NAME to event.event,
                        Event.DATE_EVENT to event.dateEvent,
                        Event.ID_HOME_TEAM to event.idHome,
                        Event.HOME_TEAM_NAME to event.homeTeam,
                        Event.HOME_SCORE to event.homeScore,
                        Event.HOME_GOAL_DETAILS to event.homeGoals,
                        Event.HOME_RED_CARDS to event.homeRedCards,
                        Event.HOME_YELLOW_CARDS to event.homeYellowCards,
                        Event.HOME_GOALKEEPER to event.homeLineupGoalKeeper,
                        Event.HOME_DEFENSE to event.homeLineupDefense,
                        Event.HOME_MIDFIELD to event.homeLineupMidfield,
                        Event.HOME_FORWARD to event.homeLineupForward,
                        Event.HOME_SUBSTITUTES to event.homeLineupSubstitutes,
                        Event.HOME_FORMATION to event.homeFormation,
                        Event.ID_AWAY_TEAM to event.idAway,
                        Event.AWAY_TEAM_NAME to event.awayTeam,
                        Event.AWAY_SCORE to event.awayScore,
                        Event.AWAY_GOAL_DETAILS to event.awayGoals,
                        Event.AWAY_RED_CARDS to event.awayRedCards,
                        Event.AWAY_YELLOW_CARDS to event.awayYellowCards,
                        Event.AWAY_GOAL_KEEPER to event.awayLineupGoalKeeper,
                        Event.AWAY_DEFENSE to event.awayLineupDefense,
                        Event.AWAY_MIDFIELD to event.awayLineupMidfield,
                        Event.AWAY_FORWARD to event.awayLineupForward,
                        Event.AWAY_SUBTITUTES to event.awayLineupSubstitutes,
                        Event.AWAY_FORMATION to event.awayFormation)
            }

            view?.addedAsFavorite(true)
        } catch (e : SQLiteConstraintException){
            logging(e.localizedMessage)
        }
    }

    override fun deleteFromFavorite(eventId: String) {
        try {
            view?.getContext()?.database?.use {
                delete(Event.TABLE_EVENT, "(${Event.ID_EVENT} = {id})",
                        "id" to eventId)
            }

            view?.addedAsFavorite(false)
        } catch (e : SQLiteConstraintException){
            logging(e.localizedMessage)
        }
    }

    override fun checkFavoriteState(eventId: String) {
        view?.getContext()?.database?.use {
            val result = select(Event.TABLE_EVENT)
                    .whereArgs("(${Event.ID_EVENT} = {id})",
                            "id" to eventId)
            val favorite = result.parseList(classParser<Event>())
            if (!favorite.isEmpty()) {
                view?.isFavorite(true)
            }
            else {
                view?.isFavorite(false)
            }
        }
    }
}