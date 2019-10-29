package com.yhn.githubclient.data.source

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    /*
    Client ID
    27dab29469f04646179e

    Client Secret
    ba458d4eb2eec3f5b1c87d44634000e044bc5b3b
    * */
//    @GET("login/oauth/authorize")
//    fun authorize(@Path)

    companion object {
        //todo move url to a proper place
        const val BASE_URL = "https://api.github.com"
    }


}