package com.yhn.githubclient.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.yhn.githubclient.data.source.GithubService
import com.yhn.githubclient.domain.GetReposUseCase
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class RepoViewModel @Inject constructor() : ViewModel() {
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val httpClient = OkHttpClient.Builder().apply { addInterceptor(logging) }.build()

    var gson = GsonBuilder()
        .setLenient()
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()
    val service = retrofit.create(GithubService::class.java)

    fun start() {
        val getReposUseCase = GetReposUseCase(service)
        viewModelScope.launch {
            getReposUseCase.invoke()
        }
    }
}