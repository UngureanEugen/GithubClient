package com.yhn.githubclient

import com.yhn.githubclient.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class DemoApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}