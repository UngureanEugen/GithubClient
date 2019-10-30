package com.yhn.githubclient.di

import android.content.Context
import com.yhn.githubclient.DemoApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepoListModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        RepoUseCaseModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DemoApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}