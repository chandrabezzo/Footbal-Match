package com.bezzo.core.data.network

object SportsRepo {

    fun getTeam(teamName : String) : String {
        return ApiEndPoint.GET_TEAM + "?t=$teamName"
    }

    fun getNextEvent(leagueId : String) : String {
        return ApiEndPoint.GET_NEXT_EVENT_BY_LEAGUE_ID + "?id=$leagueId"
    }

    fun getPastEvent(leagueId: String) : String {
        return ApiEndPoint.GET_PAST_EVENT_BY_LEAGUE_ID + "?id=$leagueId"
    }

    fun getLeague() : String {
        return ApiEndPoint.GET_LEAGUE
    }

    fun searchMatch(query : String) : String {
        return ApiEndPoint.SEARCH_EVENT + "?e=$query"
    }

    fun getTeams(leagueId: String) : String {
        return ApiEndPoint.GET_TEAMS + "?id=$leagueId"
    }

    fun searchTeams(query : String) : String {
        return ApiEndPoint.SEARCH_TEAM + "?t=$query"
    }

    fun getPlayers(idTeam : String) : String {
        return ApiEndPoint.PLAYERS + "?id=$idTeam"
    }
}