package com.yhn.githubclient.di

import androidx.lifecycle.ViewModel
import com.yhn.githubclient.repo.RepoListFragment
import com.yhn.githubclient.repo.RepoViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class RepoListModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun repoListFragment(): RepoListFragment

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    internal abstract fun bindViewModel(viewmodel: RepoViewModel): ViewModel
}