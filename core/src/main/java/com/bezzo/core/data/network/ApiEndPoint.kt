package com.bezzo.core.data.network

import com.bezzo.core.*

/**
 * Created by bezzo on 25/09/17.
 */

object ApiEndPoint {
    const val GET_NEXT_EVENT_BY_LEAGUE_ID = BuildConfig.BASE_URL + "api/v1/json/1/eventsnextleague.php"
    const val GET_PAST_EVENT_BY_LEAGUE_ID = BuildConfig.BASE_URL + "api/v1/json/1/eventspastleague.php"
    const val GET_TEAM = BuildConfig.BASE_URL + "api/v1/json/1/searchteams.php"
    const val GET_LEAGUE = BuildConfig.BASE_URL + "api/v1/json/1/all_leagues.php"
    const val SEARCH_EVENT = BuildConfig.BASE_URL + "api/v1/json/1/searchevents.php"
    const val GET_TEAMS = BuildConfig.BASE_URL + "api/v1/json/1/lookup_all_teams.php"
    const val SEARCH_TEAM = BuildConfig.BASE_URL + "api/v1/json/1/searchteams.php"
    const val PLAYERS = BuildConfig.BASE_URL + "api/v1/json/1/lookup_all_players.php"

}// This class is not publicly instantiable
