package com.bezzo.football2.di.builder

import com.bezzo.football2.di.FootballProvider
import com.bezzo.football2.features.match.detail.DetailActivity
import com.bezzo.football2.features.match.detail.DetailModule
import com.bezzo.football2.features.main.MainActivity
import com.bezzo.football2.features.main.MainModule
import com.bezzo.football2.features.match.search.SearchMatchActivity
import com.bezzo.football2.features.playerDetail.PlayerDetailActivity
import com.bezzo.football2.features.teamDetail.TeamDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class), (FootballProvider::class)])
    abstract fun bindMain() : MainActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun bindDetail() : DetailActivity

    @ContributesAndroidInjector(modules = [])
    abstract fun bindSearchMatch() : SearchMatchActivity

    @ContributesAndroidInjector(modules = [FootballProvider::class])
    abstract fun bindTeamDetail() : TeamDetailActivity

    @ContributesAndroidInjector(modules = [])
    abstract fun bindPlayerDetail() : PlayerDetailActivity
}