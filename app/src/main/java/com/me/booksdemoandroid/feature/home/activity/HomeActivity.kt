package com.me.booksdemoandroid.feature.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.home.adapter.BookAdapter
import com.me.booksdemoandroid.feature.home.helper.NavHome
import com.me.booksdemoandroid.feature.home.model.Book
import com.me.booksdemoandroid.feature.home.vm.HomeViewModel
import com.me.booksdemoandroid.shared.extension.getViewModel
import com.me.booksdemoandroid.shared.extension.showSnackBar
import com.me.booksdemoandroid.shared.repositories.Status
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_register.*

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

//        vm.loading.observe(this, Observer {
//            if (it) {
//
//            } else {
//
//            }
//        })
//
//        vm.result.observe(this, Observer {
//            if (it.first) {
//
//            }
//        })

        vm.books.observe(this, Observer {

            when (it?.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    it.data?.let { it ->
                        bookAdapter.setData(it)
                    }
                }
                Status.ERROR -> {
                    it.message?.let { message ->
                        container.showSnackBar(message, Snackbar.LENGTH_SHORT)
                    }

                }
            }
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
        bookAdapter = BookAdapter(this) { book: Book ->
            NavHome.showBookDetailActivity(this,book)
        }
        recyclerView.adapter = bookAdapter
    }


}
