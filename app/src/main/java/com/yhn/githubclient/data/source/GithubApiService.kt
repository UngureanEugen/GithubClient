package com.yhn.githubclient.data.source

import com.yhn.githubclient.data.AccessToken
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubApiService {

    @GET("search/code")
    suspend fun getAccessToken(
        @Header("Accept") acceptType: String,
        @Header("Authorization") accessToken: String,
        @Path("q") query: String,
        @Field("per_page") perPage: Int,
        @Field("page") page: Int
    ): AccessToken?


    companion object {
        //todo move url to a proper place
        const val BASE_URL = "https://api.github.com"
    }

    /*
       @Headers(
        "Accept: application/vnd.github.v3+json",
        "Authorization: token ${CredentialHelper.accessToken}"
    )
    * */

//https://api.github.com/search/code?q=addClass+user:mozilla&per_page=50&page=2

}