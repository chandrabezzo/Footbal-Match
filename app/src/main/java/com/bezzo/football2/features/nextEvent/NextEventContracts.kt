package com.bezzo.football2.features.nextEvent

import com.bezzo.core.data.model.Event

class NextEventContracts {

    interface View {
        fun showEvents(values : List<Event>)

        fun showProgressBar()

        fun hideProgressBar()
    }
}