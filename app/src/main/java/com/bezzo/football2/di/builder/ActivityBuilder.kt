package com.bezzo.football2.di.builder

import com.bezzo.football2.di.EventProvider
import com.bezzo.football2.features.detail.DetailActivity
import com.bezzo.football2.features.detail.DetailModule
import com.bezzo.football2.features.main.MainActivity
import com.bezzo.football2.features.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class), (EventProvider::class)])
    abstract fun bindMain() : MainActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun bindDetail() : DetailActivity
}