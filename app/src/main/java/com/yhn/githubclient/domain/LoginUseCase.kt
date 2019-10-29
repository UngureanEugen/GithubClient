package com.yhn.githubclient.domain

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.yhn.githubclient.data.source.CredentialHelper
import com.yhn.githubclient.data.source.CredentialHelper.clientId
import com.yhn.githubclient.data.source.CredentialHelper.redirectUrl

class LoginUseCase(private val activity: Activity?) {

    fun invoke() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/login/oauth/authorize?client_id=${clientId}&scope=repo&redirect_uri=$redirectUrl")
        )
        activity?.startActivity(intent)
    }
}