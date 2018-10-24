package com.bezzo.football2.features.favoriteEvent

import com.bezzo.core.base.BaseFragmentView
import com.bezzo.core.base.BasePresenterContract
import com.bezzo.core.data.model.Event

class FavoriteEventContracts {

    interface View : BaseFragmentView {
        fun showEvents()

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter<V : View> : BasePresenterContract<V> {

    }
}