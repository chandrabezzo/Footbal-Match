package com.bezzo.football2.features.favoriteEvent

import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.local.LocalStorageHelper
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.network.ApiHelper
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProvider
import com.bezzo.football2.utils.database
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import javax.inject.Inject

class FavoriteEventPresenter<V : FavoriteEventContracts.View> @Inject
constructor(apiHelper: ApiHelper, sessionHelper: SessionHelper, localHelper: LocalStorageHelper,
            schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(apiHelper, sessionHelper, localHelper, schedulerProvider, compositeDisposable),
        FavoriteEventContracts.Presenter<V> {
}