package com.yhn.githubclient.domain

import com.yhn.githubclient.data.model.AccessToken
import com.yhn.githubclient.data.source.CredentialHelper.clientId
import com.yhn.githubclient.data.source.CredentialHelper.clientSecret
import com.yhn.githubclient.data.source.CredentialHelper.code
import com.yhn.githubclient.data.source.GithubAuthService

class GetAccessTokenUseCase(val githubAuth: GithubAuthService) {
    suspend operator fun invoke(): AccessToken? {
        return githubAuth.getAccessToken(
            clientId = clientId.second,
            clientSecret = clientSecret.second,
            code = code
        )
    }
}