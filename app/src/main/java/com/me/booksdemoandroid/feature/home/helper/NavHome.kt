package com.me.booksdemoandroid.feature.home.helper

import android.content.Context
import android.content.Intent
import com.me.booksdemoandroid.feature.home.activity.DetailBookActivity
import com.me.booksdemoandroid.feature.home.activity.HomeActivity
import com.me.booksdemoandroid.feature.home.model.Book

class NavHome {
    companion object {

        val BOOK = "BOOK"

        fun showHomeActivity(context: Context): Intent {
            val i = Intent(context, HomeActivity::class.java)
            return i
        }

        fun showBookDetailActivity(context: Context,book: Book): Intent {
            val i = Intent(context, DetailBookActivity::class.java)
            i.putExtra(BOOK,book)
            return i
        }
    }
}