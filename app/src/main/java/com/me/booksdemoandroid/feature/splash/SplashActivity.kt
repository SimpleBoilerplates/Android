package com.me.booksdemoandroid.feature.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.me.booksdemoandroid.App
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.auth.helper.NavAuth
import com.me.booksdemoandroid.feature.home.NavHome
import com.me.booksdemoandroid.shared.helper.PreferenceHelper
import com.me.booksdemoandroid.shared.k.KEnum
import com.me.booksdemoandroid.shared.helper.PreferenceHelper.get
import com.me.booksdemoandroid.shared.repositories.Pref

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val prefs = PreferenceHelper.defaultPrefs(this)
        val token : String? = prefs[KEnum.Companion.SharedPref.Token.name]

        if (token != null) {
            Pref.token = token
            App.isAuthenticated = true
            startActivity(NavHome.showHomeActivity(this))
            finish()
        } else {
            startActivity(NavAuth.showLoginActivity(this))
            finish()
        }
    }
}
