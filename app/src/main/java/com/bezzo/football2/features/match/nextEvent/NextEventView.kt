package com.bezzo.football2.features.match.nextEvent

import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.LeagueResponse

interface NextEventView {
    fun showEvents(values : List<Event>)

    fun showProgressBar()

    fun hideProgressBar()

    fun showLeagues(values : List<LeagueResponse.League>)
}