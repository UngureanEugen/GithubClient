package com.yhn.githubclient.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.yhn.githubclient.data.Result
import com.yhn.githubclient.data.source.CredentialHelper
import com.yhn.githubclient.data.source.GithubApiService
import com.yhn.githubclient.data.source.GithubAuthService
import com.yhn.githubclient.domain.GetReposUseCase
import com.yhn.githubclient.domain.SearchReposUseCase
import com.yhn.githubclient.util.apiCall
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class RepoListViewModel @Inject constructor() : ViewModel() {

    //    private val repos = MutableLiveData<Task>
    val error = MutableLiveData<String>()

    //todo move this dependencies outside
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

    val getReposUseCase = GetReposUseCase(service)
    val searchReposUseCase = SearchReposUseCase(apiService)

    fun start() {

        viewModelScope.launch {
            val result = apiCall { getReposUseCase.invoke() }
            when (result) {
                is Result.Success -> if (result.data?.error != null) {
                    error.postValue(result.data.error)
                } else {
                    CredentialHelper.accessToken = result.data?.accessToken ?: ""
                }
                is Result.Error -> ""//show error
                Result.Loading -> "" //progress
            }
        }
    }

    fun search(query: String?, page: Int) {
        var coroutineContext: CoroutineContext? = null

        viewModelScope.launch {
            val result = apiCall {
                Log.e("bla", "invoked")
                searchReposUseCase.invoke(query ?: "", page)
            }
            Log.e("tag", result.toString())
        }
    }
}