package com.bezzo.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PlayerResponse {
    @SerializedName("player")
    @Expose
    var player: List<Player>? = null

    class Player {
        @SerializedName("idPlayer")
        @Expose
        var idPlayer: String? = null
        @SerializedName("idTeam")
        @Expose
        var idTeam: String? = null
        @SerializedName("strPlayer")
        @Expose
        var strPlayer: String? = null
        @SerializedName("dateBorn")
        @Expose
        var dateBorn: String? = null
        @SerializedName("strDescriptionEN")
        @Expose
        var strDescriptionEN: String? = null
        @SerializedName("strPosition")
        @Expose
        var strPosition: String? = null
        @SerializedName("strHeight")
        @Expose
        var strHeight: String? = null
        @SerializedName("strWeight")
        @Expose
        var strWeight: String? = null
        @SerializedName("strThumb")
        @Expose
        var strThumb: String? = null
        @SerializedName("strCutout")
        @Expose
        var strCutout: String? = null
    }
}