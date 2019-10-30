package com.yhn.githubclient.di

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yhn.githubclient.data.source.GithubApiService
import com.yhn.githubclient.data.source.GithubAuthService
import com.yhn.githubclient.domain.GetAccessTokenUseCase
import com.yhn.githubclient.repo.RepoListFragment
import com.yhn.githubclient.repo.RepoListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class RepoListModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun repoListFragment(): RepoListFragment

    @Binds
    @IntoMap
    @ViewModelKey(RepoListViewModel::class)
    internal abstract fun bindViewModel(viewmodel: RepoListViewModel): ViewModel
}