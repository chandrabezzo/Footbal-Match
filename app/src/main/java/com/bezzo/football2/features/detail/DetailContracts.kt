package com.bezzo.football2.features.detail

import com.bezzo.core.base.BaseActivityView
import com.bezzo.core.base.BasePresenterContract
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.Team

class DetailContracts {

    interface View : BaseActivityView {
        fun show(value : Event)

        fun homeTeam(value : Team)

        fun homeEvent(value : Event)

        fun awayTeam(value : Team)

        fun awayEvent(value : Event)

        fun addedAsFavorite(isFavorite : Boolean)

        fun isFavorite(state : Boolean)

        fun setFavorite()
    }

    interface Presenter<V : View> : BasePresenterContract<V> {
        fun getHomeBadge(idTeam : String?, teamName : String?)

        fun getAway(idTeam : String?, teamName : String?)

        fun addToFavorite(event : Event)

        fun deleteFromFavorite(eventId : String)

        fun checkFavoriteState(eventId : String)
    }
}