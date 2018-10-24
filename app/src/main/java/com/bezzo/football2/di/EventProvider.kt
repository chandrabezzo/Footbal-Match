package com.bezzo.football2.di

import com.bezzo.football2.features.nextEvent.NextEventFragment
import com.bezzo.football2.features.pastEvent.PastEventFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class EventProvider {

    @ContributesAndroidInjector(modules = [(EventModule::class)])
    abstract fun bindNextEvent() : NextEventFragment

    @ContributesAndroidInjector(modules = [(EventModule::class)])
    abstract fun bindPastEvent() : PastEventFragment
}