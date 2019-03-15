package com.me.booksdemoandroid.feature.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.home.adapter.BookAdapter
import com.me.booksdemoandroid.feature.home.vm.HomeViewModel
import com.me.booksdemoandroid.shared.extension.getViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var bookAdapter: BookAdapter

    private val vm: HomeViewModel by lazy {
        getViewModel { HomeViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUI()
        setVM()
    }

    private fun setVM() {

        vm.loading.observe(this, Observer {
            if (it) {

            } else {

            }
        })

        vm.result.observe(this, Observer {
            if (it.first) {

            }
        })

        vm.books.observe(this, Observer {
            bookAdapter.setData(it)
        })
        vm.fetchBooks()
    }

    private fun setUI() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            this,
            androidx.recyclerview.widget.RecyclerView.VERTICAL,
            false
        )
        bookAdapter = BookAdapter(this) { position: Int ->

        }
        recyclerView.adapter = bookAdapter

    }
}
