package com.bezzo.core.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.bezzo.core.util.constanta.AppConstans
import com.google.gson.annotations.SerializedName

@Entity(tableName = AppConstans.EVENT)
class Event {

    @PrimaryKey
    @NonNull
    @SerializedName("idEvent")
    @ColumnInfo(name = "idEvent")
    var idEvent : String = ""

    @SerializedName("strEvent")
    @ColumnInfo(name = "strEvent")
    var event : String? = null

    @SerializedName("dateEvent")
    @ColumnInfo(name = "dateEvent")
    var dateEvent : String? = null

    // # HOME
    @SerializedName("idHomeTeam")
    @ColumnInfo(name = "idHomeTeam")
    var idHome : String? = null

    @SerializedName("strHomeTeam")
    @ColumnInfo(name = "strHomeTeam")
    var homeTeam : String? = null

    @SerializedName("intHomeScore")
    @ColumnInfo(name = "intHomeScore")
    var homeScore : String? = null

    @SerializedName("strHomeGoalDetails")
    @ColumnInfo(name = "strHomeGoalDetails")
    var homeGoals : String? = null

    @SerializedName("strHomeRedCards")
    @ColumnInfo(name = "strHomeRedCards")
    var homeRedCards : String? = null

    @SerializedName("strHomeYellowCards")
    @ColumnInfo(name = "strHomeYellowCards")
    var homeYellowCards : String? = null

    @SerializedName("strHomeLineupGoalkeeper")
    @ColumnInfo(name = "strHomeLineupGoalkeeper")
    var homeLineupGoalKeeper : String? = null

    @SerializedName("strHomeLineupDefense")
    @ColumnInfo(name = "strHomeLineupDefense")
    var homeLineupDefense : String? = null

    @SerializedName("strHomeLineupMidfield")
    @ColumnInfo(name = "strHomeLineupMidfield")
    var homeLineupMidfield : String? = null

    @SerializedName("strHomeLineupForward")
    @ColumnInfo(name = "strHomeLineupForward")
    var homeLineupForward : String? = null

    @SerializedName("strHomeLineupSubstitutes")
    @ColumnInfo(name = "strHomeLineupSubstitutes")
    var homeLineupSubstitutes : String? = null

    @SerializedName("strHomeFormation")
    @ColumnInfo(name = "strHomeFormation")
    var homeFormation : String? = null

    // # AWAY
    @SerializedName("idAwayTeam")
    @ColumnInfo(name = "idAwayTeam")
    var idAway : String? = null

    @SerializedName("strAwayTeam")
    @ColumnInfo(name = "strAwayTeam")
    var awayTeam : String? = null

    @SerializedName("intAwayScore")
    @ColumnInfo(name = "intAwayScore")
    var awayScore : String? = null

    @SerializedName("strAwayGoalDetails")
    @ColumnInfo(name = "strHomeGoalDetails")
    var awayGoals : String? = null

    @SerializedName("strAwayRedCards")
    @ColumnInfo(name = "strHomeRedCards")
    var awayRedCards : String? = null

    @SerializedName("strAwayYellowCards")
    @ColumnInfo(name = "strHomeYellowCards")
    var awayYellowCards : String? = null

    @SerializedName("strAwayLineupGoalkeeper")
    @ColumnInfo(name = "strHomeLineupGoalkeeper")
    var awayLineupGoalKeeper : String? = null

    @SerializedName("strAwayLineupDefense")
    @ColumnInfo(name = "strHomeLineupDefense")
    var awayLineupDefense : String? = null

    @SerializedName("strAwayLineupMidfield")
    @ColumnInfo(name = "strHomeLineupMidfield")
    var awayLineupMidfield : String? = null

    @SerializedName("strAwayLineupForward")
    @ColumnInfo(name = "strHomeLineupForward")
    var awayLineupForward : String? = null

    @SerializedName("strAwayLineupSubstitutes")
    @ColumnInfo(name = "strHomeLineupSubstitutes")
    var awayLineupSubstitutes : String? = null

    @SerializedName("strAwayFormation")
    @ColumnInfo(name = "strHomeFormation")
    var awayFormation : String? = null
}