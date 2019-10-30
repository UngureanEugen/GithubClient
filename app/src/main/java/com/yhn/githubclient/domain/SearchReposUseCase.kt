package com.yhn.githubclient.domain

import com.yhn.githubclient.data.model.RepoListResponse
import com.yhn.githubclient.data.source.CredentialHelper
import com.yhn.githubclient.data.source.GithubApiService

//todo add request cache!!!
class SearchReposUseCase(val githubApi: GithubApiService) {
    suspend operator fun invoke(query: String, page: Int): RepoListResponse {
        return githubApi.search(
            acceptType = "application/vnd.github.v3+json",
            accessToken = "token ${CredentialHelper.accessToken}",
            query = query, perPage = 2, page = page
        )
    }
}