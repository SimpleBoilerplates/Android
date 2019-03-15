package com.me.booksdemoandroid.feature.auth.helper

import android.content.Context
import android.content.Intent
import com.me.booksdemoandroid.feature.auth.activity.LoginActivity
import com.me.booksdemoandroid.feature.auth.activity.RegisterActivity

class AuthNav {
    companion object {
        fun showLoginActivity(context: Context): Intent {
            val i = Intent(context, LoginActivity::class.java)
            return i
        }

        fun showRegisterActivity(context: Context): Intent {
            val i = Intent(context, RegisterActivity::class.java)
            return i
        }
    }
}