package com.bezzo.football.features.detail

import com.bezzo.core.base.BaseActivityView
import com.bezzo.core.base.BasePresenterContract

class DetailContracts {

    interface View : BaseActivityView {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}