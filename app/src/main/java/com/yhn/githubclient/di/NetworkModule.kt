package com.yhn.githubclient.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yhn.githubclient.data.source.GithubApiService
import com.yhn.githubclient.data.source.GithubAuthService
import com.yhn.githubclient.domain.GetAccessTokenUseCase
import com.yhn.githubclient.domain.SearchReposUseCase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder().apply { addInterceptor(logging) }.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideGithubAuthService(gson: Gson, httpClient: OkHttpClient): GithubAuthService {
        val retrofitAuth = Retrofit.Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
        return retrofitAuth.create(GithubAuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideGithubApiService(gson: Gson, httpClient: OkHttpClient): GithubApiService {
        val retrofitApi = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
        return retrofitApi.create(GithubApiService::class.java)
    }
}