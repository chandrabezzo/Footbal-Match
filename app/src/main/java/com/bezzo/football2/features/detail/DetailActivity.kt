package com.bezzo.football2.features.detail

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.Team
import com.bezzo.core.util.DateTimeUtils
import com.bezzo.core.util.GlideApp
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailContracts.View {

    @Inject
    lateinit var presenter : DetailPresenter<DetailContracts.View>

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail)
        presenter.onAttach(this)

        setActionBarTitle(getString(R.string.match_detail))
        displayHome()

        var event = presenter.gson.fromJson<Event>(dataReceived?.getString(ApiConstans.DATA),
                Event::class.java)

        presenter.getHomeBadge(event.idHome, event.homeTeam)
        presenter.getAway(event.idAway, event.awayTeam)

        show(event)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun show(value: Event) {
        var dateInEpoch = DateTimeUtils.dateToEpoch(value.dateEvent)
        tv_event_date.text = DateTimeUtils.epochToHumanDate(dateInEpoch)

        homeEvent(value)
        awayEvent(value)
    }

    override fun homeTeam(value: Team) {
        GlideApp.with(this).load(value.teamBadge).into(iv_home_badge)
        tv_home_name.text = value.teamName
    }

    override fun homeEvent(value: Event) {
        tv_home_score.text = value.homeScore
        tv_home_formation.text = value.homeFormation
        if (value.homeGoals != null && value.homeGoals != ""){
            val goals = value.homeGoals!!.split(";")
            for (goal in goals){
                tv_home_goals.append("$goal\n")
            }
        }
        if (value.homeRedCards != null && value.homeRedCards != ""){
            val redCards = value.homeRedCards!!.split(";")
            for (redCard in redCards){
                tv_home_red.append("$redCard\n")
            }
        }
        if (value.homeYellowCards != null && value.homeYellowCards != ""){
            val yellowCards = value.homeYellowCards!!.split(";")
            for (yellowCard in yellowCards){
                tv_home_yellow.append("$yellowCard\n")
            }
        }
        if (value.homeLineupGoalKeeper != null && value.homeLineupGoalKeeper != ""){
            val goalKeepers = value.homeLineupGoalKeeper!!.split(";")
            for (goalKeeper in goalKeepers){
                tv_home_keeper.append("$goalKeeper\n")
            }
        }
        if (value.homeLineupDefense != null && value.homeLineupDefense != ""){
            val defenses = value.homeLineupDefense!!.split(";")
            for (defense in defenses){
                tv_home_defense.append("$defense\n")
            }
        }
        if (value.homeLineupMidfield != null && value.homeLineupMidfield != ""){
            val midfields = value.homeLineupMidfield!!.split(";")
            for (midfield in midfields){
                tv_home_midfield.append("$midfield\n")
            }
        }
        if (value.homeLineupForward != null && value.homeLineupForward != ""){
            val forwards = value.homeLineupForward!!.split(";")
            for (forward in forwards){
                tv_home_forward.append("$forward\n")
            }
        }
        if (value.homeLineupSubstitutes != null && value.homeLineupSubstitutes != ""){
            val subtitutes = value.homeLineupSubstitutes!!.split(";")
            for (subtitute in subtitutes){
                tv_home_subtitutes.append("$subtitute\n")
            }
        }
    }

    override fun awayTeam(value: Team) {
        GlideApp.with(this).load(value.teamBadge).into(iv_away_badge)
        tv_away_name.text = value.teamName
    }

    override fun awayEvent(value: Event) {
        tv_away_score.text = value.awayScore
        tv_away_formation.text = value.awayFormation
        if (value.awayGoals != null && value.awayGoals != ""){
            val goals = value.awayGoals!!.split(";")
            for (goal in goals){
                tv_away_goals.append("$goal\n")
            }
        }
        if (value.awayRedCards != null && value.awayRedCards != ""){
            val redCards = value.awayRedCards!!.split(";")
            for (redCard in redCards){
                tv_away_red.append("$redCard\n")
            }
        }
        if (value.awayYellowCards != null && value.awayYellowCards != ""){
            val yellowCards = value.awayYellowCards!!.split(";")
            for (yellowCard in yellowCards){
                tv_away_yellow.append("$yellowCard\n")
            }
        }
        if (value.awayLineupGoalKeeper != null && value.awayLineupGoalKeeper != ""){
            val goalKeepers = value.awayLineupGoalKeeper!!.split(";")
            for (goalKeeper in goalKeepers){
                tv_away_keeper.append("$goalKeeper\n")
            }
        }
        if (value.awayLineupDefense != null && value.awayLineupDefense != ""){
            val defenses = value.awayLineupDefense!!.split(";")
            for (defense in defenses){
                tv_away_defense.append("$defense\n")
            }
        }
        if (value.awayLineupMidfield != null && value.awayLineupMidfield != ""){
            val midfields = value.awayLineupMidfield!!.split(";")
            for (midfield in midfields){
                tv_away_midfield.append("$midfield\n")
            }
        }
        if (value.awayLineupForward != null && value.awayLineupForward != ""){
            val forwards = value.awayLineupForward!!.split(";")
            for (forward in forwards){
                tv_away_forward.append("$forward\n")
            }
        }
        if (value.awayLineupSubstitutes != null && value.awayLineupSubstitutes != ""){
            val subtitutes = value.awayLineupSubstitutes!!.split(";")
            for (subtitute in subtitutes){
                tv_away_subtitutes.append("$subtitute\n")
            }
        }
    }
}
