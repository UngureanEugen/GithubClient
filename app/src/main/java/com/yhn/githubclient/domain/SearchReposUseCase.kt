package com.yhn.githubclient.domain

import com.yhn.githubclient.data.model.RepoListResponse
import com.yhn.githubclient.data.source.CredentialHelper
import com.yhn.githubclient.data.source.GithubApiService
import java.text.SimpleDateFormat
import java.util.*

class SearchReposUseCase(val githubApi: GithubApiService) {
    private val dateFormat = SimpleDateFormat("YYYY-MM-DD HH:MM:SSZ", Locale.getDefault())

    suspend operator fun invoke(query: String, page: Int): RepoListResponse {
        return githubApi.search(
            modifiedSince = dateFormat.format(Date()),
            acceptType = "application/vnd.github.v3+json",
            accessToken = "token ${CredentialHelper.accessToken}",
            query = query, perPage = PAGE_SIZE, page = page
        )
    }
    companion object {
        const val PAGE_SIZE = 50
    }
}