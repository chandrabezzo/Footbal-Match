package com.bezzo.core.data.network

import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bezzo on 11/01/18.
 */

@Singleton
class ApiHelper @Inject
constructor(val schedulerProvider: SchedulerProvider) {

    @Inject
    lateinit var session : SessionHelper

    var sportsRepo = SportsRepo(schedulerProvider)
}
