package com.bezzo.football2.di

import com.bezzo.football2.features.favoriteEvent.FavoriteEventContracts
import com.bezzo.football2.features.favoriteEvent.FavoriteEventPresenter
import com.bezzo.football2.features.nextEvent.NextEventContracts
import com.bezzo.football2.features.nextEvent.NextEventPresenter
import com.bezzo.football2.features.pastEvent.PastEventContracts
import com.bezzo.football2.features.pastEvent.PastEventPresenter
import dagger.Module
import dagger.Provides

@Module
class EventModule {

//    @Provides
//    fun provideNextEventPresenter(presenter : NextEventPresenter<NextEventContracts.View>)
//        : NextEventContracts.Presenter<NextEventContracts.View> {
//        return presenter
//    }
//
//    @Provides
//    fun providePastEventPresenter(presenter : PastEventPresenter<PastEventContracts.View>)
//        : PastEventContracts.Presenter<PastEventContracts.View> {
//        return presenter
//    }
//
//    @Provides
//    fun provideFavoriteEventPresenter(presenter : FavoriteEventPresenter<FavoriteEventContracts.View>)
//        : FavoriteEventContracts.Presenter<FavoriteEventContracts.View> {
//        return presenter
//    }
}