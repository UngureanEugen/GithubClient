package com.yhn.githubclient.di

import com.yhn.githubclient.data.source.GithubApiService
import com.yhn.githubclient.data.source.GithubAuthService
import com.yhn.githubclient.domain.GetAccessTokenUseCase
import com.yhn.githubclient.domain.SearchReposUseCase
import dagger.Module
import dagger.Provides

@Module
class RepoUseCaseModule {

    @Provides
    fun provideGetAccessTokenUseCase(githubAuthService: GithubAuthService): GetAccessTokenUseCase {
        return GetAccessTokenUseCase(githubAuthService)
    }

    @Provides
    fun provideSearchRepoUseCase(githubApiService: GithubApiService): SearchReposUseCase {
        return SearchReposUseCase(githubApiService)
    }
}