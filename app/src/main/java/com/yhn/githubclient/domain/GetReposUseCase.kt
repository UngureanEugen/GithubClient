package com.yhn.githubclient.domain

import android.util.Log
import com.yhn.githubclient.data.AccessToken
import com.yhn.githubclient.data.source.CredentialHelper.clientId
import com.yhn.githubclient.data.source.CredentialHelper.clientSecret
import com.yhn.githubclient.data.source.CredentialHelper.code
import com.yhn.githubclient.data.source.GithubService
import retrofit2.Response
import java.lang.Exception

class GetReposUseCase(val githubApi: GithubService) {
    suspend operator fun invoke(): AccessToken? {
        try {

            val result = githubApi.getAccessToken(
                clientId = clientId.second,
                clientSecret = clientSecret.second,
                code = code.second
            )
            Log.e("TAG", "tag"+ result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}