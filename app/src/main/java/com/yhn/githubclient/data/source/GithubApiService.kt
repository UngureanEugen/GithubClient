package com.yhn.githubclient.data.source

import com.yhn.githubclient.data.model.RepoListResponse
import retrofit2.Response
import retrofit2.http.*

interface GithubApiService {

    @GET("/search/repositories")
    suspend fun search(
        @Header("If-Modified-Since") modifiedSince: String,
        @Header("Accept") acceptType: String,
        @Header("Authorization") accessToken: String,
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): RepoListResponse
}