package com.bezzo.football.di.builder

import com.bezzo.football.features.detail.DetailActivity
import com.bezzo.football.features.detail.DetailModule
import com.bezzo.football.features.main.MainActivity
import com.bezzo.football.features.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMain() : MainActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun bindDetail() : DetailActivity
}