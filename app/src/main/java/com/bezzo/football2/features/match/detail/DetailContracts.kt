package com.bezzo.football2.features.match.detail

import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.Team

class DetailContracts {

    interface View {
        fun addToFavorite(event: Event)

        fun show(value : Event)

        fun homeTeam(value : Team)

        fun homeEvent(value : Event)

        fun awayTeam(value : Team)

        fun awayEvent(value : Event)

        fun addedAsFavorite(isFavorite : Boolean)

        fun isFavorite(state : Boolean)

        fun setFavorite()

        fun deleteFromFavorite(eventId: String)

        fun checkFavoriteState(eventId: String)
    }
}