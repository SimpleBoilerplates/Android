package com.me.booksdemoandroid.feature.auth.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.auth.frag.LoginFragment
import com.me.booksdemoandroid.feature.auth.helper.AuthNav
import com.me.booksdemoandroid.shared.extension.addFragment
import com.me.booksdemoandroid.shared.listner.OnBackPressedListener


class LoginActivity : AppCompatActivity(), OnBackPressedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("LoginActivity", "addFragment")
        if (savedInstanceState == null) {
            addFragment(LoginFragment.newInstance(0), R.id.container)
        }
    }

    //OnBackPressedListener
    override fun doBack() {
        onBackPressed()
        finish()
    }

    override fun doBackWithStart() {
        startActivity(AuthNav.showRegisterActivity(this))
        finish()
    }
}
