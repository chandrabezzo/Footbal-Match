package com.bezzo.football2.di

import com.bezzo.football2.features.favorite.FavoriteFragment
import com.bezzo.football2.features.favorite.favoriteEvent.FavoriteEventFragment
import com.bezzo.football2.features.favorite.favoriteTeam.FavoriteTeamFragment
import com.bezzo.football2.features.match.MatchFragment
import com.bezzo.football2.features.match.nextEvent.NextEventFragment
import com.bezzo.football2.features.match.pastEvent.PastEventFragment
import com.bezzo.football2.features.team.TeamFragment
import com.bezzo.football2.features.teamDetail.overview.OverviewTeamFragment
import com.bezzo.football2.features.teamDetail.players.PlayersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FootballProvider {

    @ContributesAndroidInjector(modules = [])
    abstract fun bindNextEvent() : NextEventFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindPastEvent() : PastEventFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindFavoriteEvent() : FavoriteEventFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindFavoriteTeam() : FavoriteTeamFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindMatch() : MatchFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindTeams() : TeamFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindFavorites() : FavoriteFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindOverviewTeam() : OverviewTeamFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindPlayer() : PlayersFragment
}