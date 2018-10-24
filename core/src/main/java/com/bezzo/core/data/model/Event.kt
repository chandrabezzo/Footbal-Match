package com.bezzo.core.data.model

import com.bezzo.core.util.constanta.AppConstans
import com.google.gson.annotations.SerializedName

data class Event(
        @SerializedName("idEvent")
        val idEvent : String?,
        @SerializedName("strEvent")
        val event : String?,
        @SerializedName("dateEvent")
        val dateEvent : String?,
        @SerializedName("idHomeTeam")
        val idHome : String?,
        @SerializedName("strHomeTeam")
        val homeTeam : String?,
        @SerializedName("intHomeScore")
        val homeScore : String?,
        @SerializedName("strHomeGoalDetails")
        val homeGoals : String?,
        @SerializedName("strHomeRedCards")
        val homeRedCards : String?,
        @SerializedName("strHomeYellowCards")
        val homeYellowCards : String?,
        @SerializedName("strHomeLineupGoalkeeper")
        val homeLineupGoalKeeper : String?,
        @SerializedName("strHomeLineupDefense")
        val homeLineupDefense : String?,
        @SerializedName("strHomeLineupMidfield")
        val homeLineupMidfield : String?,
        @SerializedName("strHomeLineupForward")
        val homeLineupForward : String?,
        @SerializedName("strHomeLineupSubstitutes")
        val homeLineupSubstitutes : String?,
        @SerializedName("strHomeFormation")
        val homeFormation : String?,
        @SerializedName("idAwayTeam")
        val idAway : String?,
        @SerializedName("strAwayTeam")
        val awayTeam : String?,
        @SerializedName("intAwayScore")
        val awayScore : String?,
        @SerializedName("strAwayGoalDetails")
        val awayGoals : String?,
        @SerializedName("strAwayRedCards")
        val awayRedCards : String?,
        @SerializedName("strAwayYellowCards")
        val awayYellowCards : String?,
        @SerializedName("strAwayLineupGoalkeeper")
        val awayLineupGoalKeeper : String?,
        @SerializedName("strAwayLineupDefense")
        val awayLineupDefense : String?,
        @SerializedName("strAwayLineupMidfield")
        val awayLineupMidfield : String?,
        @SerializedName("strAwayLineupForward")
        val awayLineupForward : String?,
        @SerializedName("strAwayLineupSubstitutes")
        val awayLineupSubstitutes : String?,
        @SerializedName("strAwayFormation")
        val awayFormation : String?) {

    companion object {
        const val TABLE_EVENT : String = AppConstans.EVENT
        const val ID_EVENT : String = "idEvent"
        const val DATE_EVENT : String = "dateEvent"

        const val EVENT_NAME : String = "strEvent"
        const val ID_HOME_TEAM : String = "idHomeTeam"
        const val HOME_TEAM_NAME : String = "strHomeTeam"
        const val HOME_SCORE : String = "intHomeScore"
        const val HOME_GOAL_DETAILS : String = "strHomeGoalDetails"
        const val HOME_RED_CARDS : String = "strHomeRedCards"
        const val HOME_YELLOW_CARDS : String = "strHomeYellowCards"
        const val HOME_GOALKEEPER : String = "strHomeLineupGoalkeeper"
        const val HOME_DEFENSE : String = "strHomeLineupDefense"
        const val HOME_MIDFIELD : String = "strHomeLineupMidfield"
        const val HOME_FORWARD : String = "strHomeLineupForward"
        const val HOME_SUBSTITUTES : String = "strHomeLineupSubstitutes"
        const val HOME_FORMATION : String = "strHomeFormation"

        const val ID_AWAY_TEAM : String = "idAwayTeam"
        const val AWAY_TEAM_NAME : String = "strAwayTeam"
        const val AWAY_SCORE : String = "intAwayScore"
        const val AWAY_GOAL_DETAILS : String = "strAwayGoalDetails"
        const val AWAY_RED_CARDS : String = "strAwayRedCards"
        const val AWAY_YELLOW_CARDS : String = "strAwayYellowCards"
        const val AWAY_GOAL_KEEPER : String = "strAwayLineupGoalkeeper"
        const val AWAY_DEFENSE : String = "strAwayLineupDefense"
        const val AWAY_MIDFIELD : String = "strAwayLineupMidfield"
        const val AWAY_FORWARD : String = "strAwayLineupForward"
        const val AWAY_SUBTITUTES : String = "strAwayLineupSubstitutes"
        const val AWAY_FORMATION : String = "strAwayFormation"
    }
}