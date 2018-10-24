package com.bezzo.core.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.bezzo.core.util.constanta.AppConstans
import com.google.gson.annotations.SerializedName

@Entity(tableName = AppConstans.TEAM)
class Team(
        @PrimaryKey
        @NonNull
        @SerializedName("idTeam")
        @ColumnInfo(name = "idTeam")
        var idTeam : String = "",

        @SerializedName("strTeam")
        @ColumnInfo(name = "strTeam")
        var teamName : String? = null,

        @SerializedName("strTeamBadge")
        @ColumnInfo(name = "strTeamBadge")
        var teamBadge : String? = null
)