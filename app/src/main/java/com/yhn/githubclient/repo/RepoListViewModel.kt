package com.yhn.githubclient.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.yhn.githubclient.data.Result
import com.yhn.githubclient.data.model.RepoItem
import com.yhn.githubclient.data.source.CredentialHelper
import com.yhn.githubclient.data.source.GithubApiService
import com.yhn.githubclient.data.source.GithubAuthService
import com.yhn.githubclient.domain.GetReposUseCase
import com.yhn.githubclient.domain.SearchReposUseCase
import com.yhn.githubclient.util.ApiException
import com.yhn.githubclient.util.apiCall
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class RepoListViewModel @Inject constructor() : ViewModel() {

    val error = MutableLiveData<String>()
    val repos = MutableLiveData<List<RepoItem>>()
    val refreshAccessToken = MutableLiveData<Unit>()

    private var page = AtomicInteger(0)

    //todo this dependencies to di
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

    val retrofitApi = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()

    val service = retrofit.create(GithubAuthService::class.java)
    val apiService = retrofitApi.create(GithubApiService::class.java)

    private val getReposUseCase = GetReposUseCase(service)
    private val searchReposUseCase = SearchReposUseCase(apiService)

    fun verifyAccessToken() {
        if (CredentialHelper.accessToken.isNotEmpty()) return
        viewModelScope.launch {
            when (val result = apiCall { getReposUseCase.invoke() }) {
                is Result.Success -> if (result.data?.error != null) {
                    refreshAccessToken.postValue(Unit)
                } else {
                    CredentialHelper.accessToken = result.data?.accessToken ?: ""
                }
                is Result.Error -> error.postValue(result.toString())
                Result.Loading -> Unit
            }
        }
    }

    fun search(query: String, language: String = "kotlin", resetPageNumber: Boolean) {
        viewModelScope.launch {
            if (resetPageNumber) page.set(0)
            when (val result =
                apiCall {
                    searchReposUseCase.invoke(
                        "$query+language:$language",
                        page.incrementAndGet()
                    )
                }) {
                is Result.Success -> repos.postValue(result.data.items)
                is Result.Error -> {
                    error.postValue(result.exception.message)
                    if (result.exception is ApiException && result.exception.code == 403) {
                        refreshAccessToken.postValue(Unit)
                    }
                }
                Result.Loading -> Unit // no time for progress
            }
        }
    }
}