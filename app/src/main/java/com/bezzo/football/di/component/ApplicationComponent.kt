package com.bezzo.football.di.component

import android.app.Application
import com.bezzo.football.MvpApp
import com.bezzo.football.di.builder.ActivityBuilder
import com.bezzo.football.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by bezzo on 26/09/17.
 */

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (ApplicationModule::class), (ActivityBuilder::class)])
interface ApplicationComponent {

    fun inject(app: MvpApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
