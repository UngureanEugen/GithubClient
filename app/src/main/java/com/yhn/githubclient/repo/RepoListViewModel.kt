package com.yhn.githubclient.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.yhn.githubclient.data.Result
import com.yhn.githubclient.data.source.CredentialHelper
import com.yhn.githubclient.data.source.GithubAuthService
import com.yhn.githubclient.domain.GetReposUseCase
import com.yhn.githubclient.util.apiCall
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


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
    val service = retrofit.create(GithubAuthService::class.java)

    fun start() {
        val getReposUseCase = GetReposUseCase(service)
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
}