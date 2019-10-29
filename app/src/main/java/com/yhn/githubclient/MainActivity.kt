package com.yhn.githubclient

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhn.githubclient.login.LoginFragment

class MainActivity : AppCompatActivity() {

    // Client ID
    val clientId = "9fb96e9e1d6c77f20df6"

    // Client Secret
    val clientSecret = "f4b33c184c38108fb8f20ef24b301e448d02a402"

    // Redirect Url
    val redirectUrl = "bla://callback"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id="+clientId+"&scope=repo&redirect_uri="+redirectUrl))
        startActivity(intent)

     /*   getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.container, LoginFragment.newInstance(), "LoginFragment")
            .disallowAddToBackStack()
            .commit();*/
    }

    override fun onResume() {
        super.onResume()

    }
}
