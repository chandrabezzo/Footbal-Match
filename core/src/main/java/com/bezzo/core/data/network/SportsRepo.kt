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

}