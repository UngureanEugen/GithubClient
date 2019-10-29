package com.yhn.githubclient

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yhn.githubclient.data.source.CredentialHelper

class AuthorizeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.data?.let {
           CredentialHelper.code = it.getQueryParameter("code") ?: ""
        }
        setResult(Activity.RESULT_OK)
        finish()
    }
}