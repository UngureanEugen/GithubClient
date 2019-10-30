package com.yhn.githubclient.domain

import com.yhn.githubclient.data.model.AccessToken
import com.yhn.githubclient.data.source.CredentialHelper.clientId
import com.yhn.githubclient.data.source.CredentialHelper.clientSecret
import com.yhn.githubclient.data.source.CredentialHelper.code
import com.yhn.githubclient.data.source.GithubAuthService

//todo modify
class GetReposUseCase(val githubApi: GithubAuthService) {
    suspend operator fun invoke(): AccessToken? {
        return githubApi.getAccessToken(
                clientId = clientId.second,
                clientSecret = clientSecret.second,
                code = code
            )
    }
}