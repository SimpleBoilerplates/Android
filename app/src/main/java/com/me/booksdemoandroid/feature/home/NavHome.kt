package com.me.booksdemoandroid.feature.home

import android.content.Context
import android.content.Intent
import com.me.booksdemoandroid.feature.auth.activity.LoginActivity

class NavHome {
    companion object {
        fun showHomeActivity(context: Context): Intent {
            val i = Intent(context, HomeActivity::class.java)
            return i
        }
    }
}