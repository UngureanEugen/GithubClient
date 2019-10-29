package com.yhn.githubclient.data.source

import com.yhn.githubclient.data.AccessToken
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubAuthService {
    /*
    Client ID
    27dab29469f04646179e

    Client Secret
    ba458d4eb2eec3f5b1c87d44634000e044bc5b3b
    * */
//    @GET("login/oauth/authorize")
//    fun authorize(@Path)


    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(@Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String,
                       @Field("code") code: String): AccessToken?


    companion object {
        //todo move url to a proper place
        const val BASE_URL = "https://api.github.com"
    }


}