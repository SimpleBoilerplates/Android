package com.me.booksdemoandroid.feature.auth.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.auth.frag.RegisterFragment
import com.me.booksdemoandroid.feature.auth.helper.AuthNav
import com.me.booksdemoandroid.shared.extension.addFragment
import com.me.booksdemoandroid.shared.listner.OnBackPressedListener
import kotlinx.android.synthetic.main.toolbar.*


class RegisterActivity : AppCompatActivity(), OnBackPressedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setToolBar("")


        if (savedInstanceState == null) {
            // Log.d(TAG,"addFragment")
            addFragment(RegisterFragment.newInstance(0), R.id.container)
        }
    }

    private fun setToolBar(title: String) {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_VISIBLE
        decorView.systemUiVisibility = uiOptions
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = title
        }
        toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    //OnBackPressedListener
    override fun doBack() {
        onBackPressed()
        finish()
    }

    override fun doBackWithStart() {
        startActivity(AuthNav.showLoginActivity(this))
        finish()
    }

}
