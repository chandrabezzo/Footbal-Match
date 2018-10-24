package com.bezzo.core.data.network

import com.bezzo.core.util.SchedulerProvider
import com.rx2androidnetworking.Rx2ANRequest

class SportsRepo constructor(val schedulerProvider: SchedulerProvider) {

    fun getTeam(teamName : String) : Rx2ANRequest {
        var params = HashMap<String, String>()
        params["t"] = teamName

        return RestApi.get(ApiEndPoint.GET_TEAM, params, null, null)
    }

    fun getNextEvent(leagueId : String) : Rx2ANRequest {
        var params = HashMap<String, String>()
        params["id"] = leagueId

        return RestApi.get(ApiEndPoint.GET_NEXT_EVENT_BY_LEAGUE_ID,  params, null, null)
    }

    fun getPastEvent(leagueId: String) : Rx2ANRequest {
        var params = HashMap<String, String>()
        params["id"] = leagueId

        return RestApi.get(ApiEndPoint.GET_PAST_EVENT_BY_LEAGUE_ID, params, null, null)
    }

}