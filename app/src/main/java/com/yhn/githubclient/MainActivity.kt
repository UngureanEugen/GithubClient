package com.yhn.githubclient

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yhn.githubclient.domain.LoginUseCase
import com.yhn.githubclient.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /*  if (intent == null) {
            val useCase = LoginUseCase(this)
            useCase.invoke()
            Log.e("TAG", "invoke")
        }
        intent?.let {
            Log.e("TAG", "" + it.data)
        }*/
    }

    override fun onResume() {
        super.onResume()

    }
}
