package com.bezzo.football2.features.teamDetail

import com.bezzo.core.base.BaseActivityView
import com.bezzo.core.data.model.Team

interface TeamDetailView : BaseActivityView {
    fun addedAsFavorite(isFavorite : Boolean)

    fun isFavorite(state : Boolean)

    fun setFavorite()

    fun deleteFromFavorite(teamId: String)

    fun checkFavoriteState(teamId: String)

    fun addToFavorite(team : Team)
}