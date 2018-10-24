package com.bezzo.core.data.network

import com.bezzo.core.*

/**
 * Created by bezzo on 25/09/17.
 */

object ApiEndPoint {
    const val GET_NEXT_EVENT_BY_LEAGUE_ID = BuildConfig.BASE_URL + "api/v1/json/1/eventsnextleague.php"
    const val GET_PAST_EVENT_BY_LEAGUE_ID = BuildConfig.BASE_URL + "api/v1/json/1/eventspastleague.php"
    const val GET_TEAM = BuildConfig.BASE_URL + "api/v1/json/1/searchteams.php"
}// This class is not publicly instantiable
