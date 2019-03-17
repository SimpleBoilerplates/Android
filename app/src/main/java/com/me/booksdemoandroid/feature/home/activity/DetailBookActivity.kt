package com.me.booksdemoandroid.feature.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.home.helper.NavHome
import com.me.booksdemoandroid.feature.home.model.Book

class DetailBookActivity : AppCompatActivity() {

    lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_book)
        intent?.let {
            book = it.getParcelableExtra(NavHome.BOOK)
        }
    }
}
