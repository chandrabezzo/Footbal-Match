package com.bezzo.core.data.model

import com.bezzo.core.util.constanta.AppConstans
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        @Expose
        var idTeam: String?,
        @SerializedName("strTeam")
        @Expose
        var strTeam: String?,
        @SerializedName("intFormedYear")
        @Expose
        var intFormedYear: String?,
        @SerializedName("idLeague")
        @Expose
        var idLeague: String?,
        @SerializedName("strStadium")
        @Expose
        var strStadium: String?,
        @SerializedName("strDescriptionEN")
        @Expose
        var strDescriptionEN: String?,
        @SerializedName("strTeamBadge")
        @Expose
        var imageTeam: String?) {

        companion object {
                const val TABLE_TEAM : String = AppConstans.TEAM
                const val ID_TEAM : String = "idTeam"
                const val TEAM_NAME : String = "strTeam"
                const val FORMED_YEAR : String = "intFormedYear"
                const val LEAGUE_ID : String = "idLeague"
                const val STADIUM : String = "strStadium"
                const val DESCRIPTION : String = "strDescriptionEN"
                const val IMAGE_TEAM : String = "strTeamBadge"
        }
}