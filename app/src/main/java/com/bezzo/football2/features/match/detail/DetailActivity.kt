package com.bezzo.football2.features.match.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.Team
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.DateTimeUtils
import com.bezzo.core.util.GlideApp
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.utils.database
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity : BaseActivity(), DetailContracts.View {

    lateinit var presenter : DetailPresenter

    lateinit var menu : Menu

    var isFavorite : Boolean = false
    lateinit var event : Event
    val request = ApiRepository()
    val gson = Gson()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail)
        presenter = DetailPresenter(this, request, gson)

        setActionBarTitle(getString(R.string.match_detail))
        displayHome()

        event = gson.fromJson<Event>(dataReceived?.getString(ApiConstans.DATA), Event::class.java)

        showProgressDialog(getString(R.string.loading), true)

        presenter.getHome(event.idHome, event.homeTeam)
        presenter.getAway(event.idAway, event.awayTeam)

        show(event)

        event.idEvent?.let { checkFavoriteState(it) }
    }

    override fun show(value: Event) {

        var dateInEpoch = value.dateEvent?.let { DateTimeUtils.dateToEpoch(it) }
        tv_event_date.text = dateInEpoch?.let { DateTimeUtils.epochToHumanDate(it) }
        tv_event_time.text = value.eventTime?.split("+")?.get(0) ?: ""

        homeEvent(value)
        awayEvent(value)

        dismissProgressDialog()
    }

    override fun homeTeam(value: Team) {
        GlideApp.with(this).load(value.imageTeam).into(iv_home_badge)
        tv_home_name.text = value.strTeam
    }

    override fun homeEvent(value: Event) {
        tv_home_score.text = value.homeScore
        tv_home_formation.text = value.homeFormation
        if (value.homeGoals != null && value.homeGoals != ""){
            val goals = value.homeGoals?.split(";")
            if (goals != null) {
                for (goal in goals){
                    tv_home_goals.append("$goal\n")
                }
            }
        }
        if (value.homeRedCards != null && value.homeRedCards != ""){
            val redCards = value.homeRedCards?.split(";")
            if (redCards != null) {
                for (redCard in redCards){
                    tv_home_red.append("$redCard\n")
                }
            }
        }
        if (value.homeYellowCards != null && value.homeYellowCards != ""){
            val yellowCards = value.homeYellowCards?.split(";")
            if (yellowCards != null) {
                for (yellowCard in yellowCards){
                    tv_home_yellow.append("$yellowCard\n")
                }
            }
        }
        if (value.homeLineupGoalKeeper != null && value.homeLineupGoalKeeper != ""){
            val goalKeepers = value.homeLineupGoalKeeper?.split(";")
            if (goalKeepers != null) {
                for (goalKeeper in goalKeepers){
                    tv_home_keeper.append("$goalKeeper\n")
                }
            }
        }
        if (value.homeLineupDefense != null && value.homeLineupDefense != ""){
            val defenses = value.homeLineupDefense?.split(";")
            if (defenses != null) {
                for (defense in defenses){
                    tv_home_defense.append("$defense\n")
                }
            }
        }
        if (value.homeLineupMidfield != null && value.homeLineupMidfield != ""){
            val midfields = value.homeLineupMidfield?.split(";")
            if (midfields != null) {
                for (midfield in midfields){
                    tv_home_midfield.append("$midfield\n")
                }
            }
        }
        if (value.homeLineupForward != null && value.homeLineupForward != ""){
            val forwards = value.homeLineupForward?.split(";")
            if (forwards != null) {
                for (forward in forwards){
                    tv_home_forward.append("$forward\n")
                }
            }
        }
        if (value.homeLineupSubstitutes != null && value.homeLineupSubstitutes != ""){
            val subtitutes = value.homeLineupSubstitutes?.split(";")
            if (subtitutes != null) {
                for (subtitute in subtitutes){
                    tv_home_subtitutes.append("$subtitute\n")
                }
            }
        }
    }

    override fun awayTeam(value: Team) {
        GlideApp.with(this).load(value.imageTeam).into(iv_away_badge)
        tv_away_name.text = value.strTeam
    }

    override fun awayEvent(value: Event) {
        tv_away_score.text = value.awayScore
        tv_away_formation.text = value.awayFormation
        if (value.awayGoals != null && value.awayGoals != ""){
            val goals = value.awayGoals?.split(";")
            if (goals != null) {
                for (goal in goals){
                    tv_away_goals.append("$goal\n")
                }
            }
        }
        if (value.awayRedCards != null && value.awayRedCards != ""){
            val redCards = value.awayRedCards?.split(";")
            if (redCards != null) {
                for (redCard in redCards){
                    tv_away_red.append("$redCard\n")
                }
            }
        }
        if (value.awayYellowCards != null && value.awayYellowCards != ""){
            val yellowCards = value.awayYellowCards?.split(";")
            if (yellowCards != null) {
                for (yellowCard in yellowCards){
                    tv_away_yellow.append("$yellowCard\n")
                }
            }
        }
        if (value.awayLineupGoalKeeper != null && value.awayLineupGoalKeeper != ""){
            val goalKeepers = value.awayLineupGoalKeeper?.split(";")
            if (goalKeepers != null) {
                for (goalKeeper in goalKeepers){
                    tv_away_keeper.append("$goalKeeper\n")
                }
            }
        }
        if (value.awayLineupDefense != null && value.awayLineupDefense != ""){
            val defenses = value.awayLineupDefense?.split(";")
            if (defenses != null) {
                for (defense in defenses){
                    tv_away_defense.append("$defense\n")
                }
            }
        }
        if (value.awayLineupMidfield != null && value.awayLineupMidfield != ""){
            val midfields = value.awayLineupMidfield?.split(";")
            if (midfields != null) {
                for (midfield in midfields){
                    tv_away_midfield.append("$midfield\n")
                }
            }
        }
        if (value.awayLineupForward != null && value.awayLineupForward != ""){
            val forwards = value.awayLineupForward?.split(";")
            if (forwards != null) {
                for (forward in forwards){
                    tv_away_forward.append("$forward\n")
                }
            }
        }
        if (value.awayLineupSubstitutes != null && value.awayLineupSubstitutes != ""){
            val subtitutes = value.awayLineupSubstitutes?.split(";")
            if (subtitutes != null) {
                for (subtitute in subtitutes){
                    tv_away_subtitutes.append("$subtitute\n")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        menu?.let {
            this.menu = it
        }
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.is_favorite -> {
                if (isFavorite) {
                    event.idEvent?.let { deleteFromFavorite(it) }
                }
                else {
                    addToFavorite(event)
                }

                isFavorite = !isFavorite
                setFavorite()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun addToFavorite(event: Event) {
        try {
            database.use {
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
                        Event.AWAY_FORMATION to event.awayFormation,
                        Event.TIME_EVENT to event.eventTime)
            }

            addedAsFavorite(true)
        } catch (e : SQLiteConstraintException){
            showToast(e.localizedMessage, Toast.LENGTH_SHORT)
        }
    }

    override fun addedAsFavorite(isFavorite : Boolean) {
        if (isFavorite){
            showToast(getString(R.string.added_as_fav), Toast.LENGTH_SHORT)
        }
        else {
            showToast(getString(R.string.deleted_as_favorite), Toast.LENGTH_SHORT)
        }
    }

    override fun isFavorite(state: Boolean) {
        isFavorite = state
    }

    override fun setFavorite() {
        if (isFavorite){
            menu.getItem(0).icon = ContextCompat.getDrawable(this,
                    R.drawable.ic_star_white)
        }
        else {
            menu.getItem(0).icon = ContextCompat.getDrawable(this,
                    R.drawable.ic_star_border_white)
        }
    }

    override fun deleteFromFavorite(eventId: String) {
        try {
            database.use {
                delete(Event.TABLE_EVENT, "(${Event.ID_EVENT} = {id})",
                        "id" to eventId)
            }

            addedAsFavorite(false)
        } catch (e : SQLiteConstraintException){
            showToast(e.localizedMessage, Toast.LENGTH_SHORT)
        }
    }

    override fun checkFavoriteState(eventId: String) {
        database.use {
            val result = select(Event.TABLE_EVENT)
                    .whereArgs("(${Event.ID_EVENT} = {id})",
                            "id" to eventId)
            val favorite = result.parseList(classParser<Event>())
            if (!favorite.isEmpty()) {
                isFavorite(true)
            }
            else {
                isFavorite(false)
            }
        }
    }
}
