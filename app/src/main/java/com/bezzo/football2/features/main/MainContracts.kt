package com.bezzo.football2.features.main

import com.bezzo.core.base.BaseActivityView
import com.bezzo.core.base.BasePresenterContract

class MainContracts {

    interface View : BaseActivityView {

    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}