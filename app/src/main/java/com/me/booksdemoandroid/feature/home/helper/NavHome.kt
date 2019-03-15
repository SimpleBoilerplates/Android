package com.me.booksdemoandroid.feature.home.helper

import android.content.Context
import android.content.Intent
import com.me.booksdemoandroid.feature.home.activity.HomeActivity

class NavHome {
    companion object {
        fun showHomeActivity(context: Context): Intent {
            val i = Intent(context, HomeActivity::class.java)
            return i
        }
    }
}