package com.me.booksdemoandroid

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

//import androidx.multidex.MultiDex


class App : Application() {

    companion object {
        //var currentUser: User? = null
        var isAuthenticated: Boolean = false

        fun setUser() {

        }
    }

    override fun onCreate() {
        super.onCreate()
        setUser()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        // MultiDex.install(base)
    }

}