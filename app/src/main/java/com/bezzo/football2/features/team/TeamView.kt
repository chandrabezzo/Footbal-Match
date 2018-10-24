package com.bezzo.football2.features.team

import com.bezzo.core.data.model.LeagueResponse
import com.bezzo.core.data.model.Team

interface TeamView {
    fun showLeagues(values : List<LeagueResponse.League>)

    fun showLoadMore()

    fun hideLoadMore()

    fun showTeams(values : List<Team>)
}