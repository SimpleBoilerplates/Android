package com.me.booksdemoandroid.feature.home.vm

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.me.booksdemoandroid.feature.home.helper.HomeRepository
import com.me.booksdemoandroid.feature.home.model.Book
import com.me.booksdemoandroid.shared.extension.Coroutines
import com.me.booksdemoandroid.shared.repositories.ApiFactory
import com.me.booksdemoandroid.shared.repositories.Resource
import com.squareup.moshi.Moshi
import org.json.JSONObject

class HomeViewModel : ViewModel() {

    // private val _loading = MutableLiveData<Boolean>()
    //  val loading: LiveData<Boolean> get() = _loading
    private val repository = HomeRepository(ApiFactory.homeApi)
    // private val _result = MutableLiveData<Pair<Boolean, String>>()
    //  val result: LiveData<Pair<Boolean, String>> get() = _result
    private val _books = MutableLiveData<Resource<ArrayList<Book>>>()
    val books: LiveData<Resource<ArrayList<Book>>> get() = _books
    private val moshi = Moshi.Builder().build()

    @UiThread
    fun fetchBooks() {
        //_loading.value = true
        Coroutines.ioThenMain({
            repository.books()
        }) {
            it?.let { it ->
                //  _loading.value = false
                val jsonObject = JSONObject(it)
                if (!jsonObject.optBoolean("error")) {
                    val jsonAdapter = moshi.adapter<ArrayList<Book>>(Array<Book>::class.java)
                    _books.value =
                        Resource.success(jsonAdapter.fromJson(jsonObject.optJSONArray("data").toString()))

                } else {
                    _books.value = Resource.error(jsonObject.optString("message"), null)
                }
            }
        }
    }
}