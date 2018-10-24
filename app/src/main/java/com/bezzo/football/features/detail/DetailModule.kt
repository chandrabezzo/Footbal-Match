package com.bezzo.football.features.detail

import com.bezzo.core.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @Provides
    @PerActivity
    fun provideDetailPresenter(presenter: DetailPresenter<DetailContracts.View>) :
            DetailContracts.Presenter<DetailContracts.View> {
        return presenter
    }
}