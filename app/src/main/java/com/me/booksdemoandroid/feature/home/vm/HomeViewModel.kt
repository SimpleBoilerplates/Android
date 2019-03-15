package com.me.booksdemoandroid.feature.home.vm

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.me.booksdemoandroid.feature.home.helper.RepositoryHome
import com.me.booksdemoandroid.feature.home.model.Book
import com.me.booksdemoandroid.shared.extension.Coroutines
import com.me.booksdemoandroid.shared.repositories.ApiFactory
import com.squareup.moshi.Moshi
import org.json.JSONObject

class HomeViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val repository = RepositoryHome(ApiFactory.homeApi)

    private val _result = MutableLiveData<Pair<Boolean, String>>()
    val result: LiveData<Pair<Boolean, String>> get() = _result

    private val _books = MutableLiveData<ArrayList<Book>>()
    val books: LiveData<ArrayList<Book>> get() = _books

    val moshi = Moshi.Builder().build()


    @UiThread
    fun fetchBooks() {

        _loading.value = true

        Coroutines.ioThenMain({
            repository.books()
        }) { it ->
            it?.let {

                _loading.value = false

                val jsonObject = JSONObject(it)
                if (!jsonObject.optBoolean("error")) {
                    _result.value = Pair(true, jsonObject.optString(""))

                    val jsonAdapter = moshi.adapter<ArrayList<Book>>(Array<Book>::class.java)
                    _books.value = jsonAdapter.fromJson(jsonObject.optJSONArray("data").toString())

                } else {
                    _result.value = Pair(false, jsonObject.optString("message"))
                }
            }
        }

//        scope.launch {
//            val popularMovies = repository.login()
//           // popularMoviesLiveData.postValue(popularMovies)
//        }

        //return result
    }

}