package com.yhn.githubclient.data.source

import com.yhn.githubclient.data.model.AccessToken
import retrofit2.http.*

interface GithubAuthService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(@Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String,
                       @Field("code") code: String): AccessToken?

}