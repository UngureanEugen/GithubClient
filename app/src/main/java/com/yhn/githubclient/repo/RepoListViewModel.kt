package com.yhn.githubclient.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.yhn.githubclient.data.Result
import com.yhn.githubclient.data.model.RepoItem
import com.yhn.githubclient.data.source.CredentialHelper
import com.yhn.githubclient.data.source.GithubApiService
import com.yhn.githubclient.data.source.GithubAuthService
import com.yhn.githubclient.domain.GetAccessTokenUseCase
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

class RepoListViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val searchReposUseCase: SearchReposUseCase
) : ViewModel() {

    private var page = AtomicInteger(0)
    private var searchJob: Job? = null

    val error = MutableLiveData<String>()
    val repos = MutableLiveData<List<RepoItem>>()
    val refreshAccessCodeToken = MutableLiveData<Unit>()

    fun verifyAccessToken() {
        viewModelScope.launch {
            when (val result = apiCall { getAccessTokenUseCase.invoke() }) {
                is Result.Success -> if (result.data?.error != null) {
                    refreshAccessCodeToken.postValue(Unit)
                } else {
                    CredentialHelper.accessToken = result.data?.accessToken ?: ""
                }
                is Result.Error -> error.postValue(result.toString())
                Result.Loading -> Unit
            }
        }
    }

    /**
     * cancel previous search job when new search is requested during the delay
     */
    fun search(query: String, language: String = "kotlin", resetPageNumber: Boolean) {
        viewModelScope.launch {
            searchJob?.cancelAndJoin()?.also { searchJob = null }
            searchJob = launch {
                delay(800)
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
                        if (result.exception is ApiException) {
                            when (result.exception.code) {
                                403 -> refreshAccessCodeToken.postValue(Unit)
                                401 -> verifyAccessToken()
                                else -> Unit
                            }
                        }
                    }
                    Result.Loading -> Unit // no time for progress
                }
            }
        }
    }
}