package com.me.booksdemoandroid.shared.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

class Connectivity {
    private lateinit var connectivityManager: ConnectivityManager
    internal var wifiInfo: NetworkInfo? = null
    internal var mobileInfo: NetworkInfo? = null
    private var connected = false

    val isOnline: Boolean
        get() {
            try {
                connectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val networkInfo = connectivityManager.activeNetworkInfo
                connected = networkInfo != null && networkInfo.isAvailable &&
                        networkInfo.isConnected
                return connected

            } catch (e: Exception) {
                println("CheckConnectivity Exception: " + e.message)
                Log.v("connectivity", e.toString())
            }

            return connected
        }

    companion object {

        internal lateinit var context: Context


        private val instance = Connectivity()

        fun getInstance(ctx: Context): Connectivity {
            context = ctx.applicationContext
            return instance
        }
    }
}

