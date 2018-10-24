package com.bezzo.football2.features.nextEvent

import com.bezzo.core.base.BaseFragmentView
import com.bezzo.core.base.BasePresenterContract
import com.bezzo.core.data.model.Event

class NextEventContracts {

    interface View : BaseFragmentView {
        fun showEvents(values : ArrayList<Event>)

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter<V : View> : BasePresenterContract<V> {
        fun getEvents(leagueId : String)
    }
}