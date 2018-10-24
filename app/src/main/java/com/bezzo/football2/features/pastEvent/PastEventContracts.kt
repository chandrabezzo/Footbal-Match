package com.bezzo.football2.features.pastEvent

import com.bezzo.core.base.BaseFragmentView
import com.bezzo.core.base.BasePresenterContract
import com.bezzo.core.data.model.Event

class PastEventContracts {

    interface View : BaseFragmentView {
        fun showEvents(values : List<Event>)

        fun showProgressBar()

        fun hideProgressBar()
    }
}