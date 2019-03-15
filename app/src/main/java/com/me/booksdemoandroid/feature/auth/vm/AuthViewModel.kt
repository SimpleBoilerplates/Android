package com.me.booksdemoandroid.feature.auth.vm

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.me.booksdemoandroid.feature.auth.helper.RepositoryAuth
import com.me.booksdemoandroid.shared.extension.Coroutines
import com.me.booksdemoandroid.shared.repositories.ApiFactory
import org.json.JSONObject


class AuthViewModel : ViewModel() {
//
//    private val parentJob = Job()
//    private val coroutineContext: CoroutineContext
//        get() = parentJob + Dispatchers.Default
//
//    private val scope = CoroutineScope(coroutineContext)
//

    private val repository = RepositoryAuth(ApiFactory.authApi)

    //private lateinit var result: MutableLiveData<Pair<Boolean,String>>

    private val _result = MutableLiveData<Pair<Boolean, String>>()
    val result: LiveData<Pair<Boolean, String>> get() = _result

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    @UiThread
    fun login(email: String, password: String) {

        _loading.value = true

        Coroutines.ioThenMain({
            repository.login(email, password)
        }) { it ->
            it?.let {

                _loading.value = false

                val jsonObject = JSONObject(it)
                if (!jsonObject.optBoolean("error")) {
                    _result.value = Pair(true, jsonObject.optString("token"))
                } else {
                    _result.value = Pair(false, jsonObject.optString("message"))
                }
            }
        }

//        scope.launch {
//            val popularMovies = repository.login()
//           // popularMoviesLiveData.postValue(popularMovies)
//        }

    }

    @UiThread
    fun signUp(name: String, email: String, password: String): LiveData<Pair<Boolean, String>> {

        _loading.value = true

        Coroutines.ioThenMain({
            repository.signUp(name, email, password)
        }) {
            it?.let {
                _loading.value = false
                //result = MutableLiveData()
                val jsonObject = JSONObject(it)
                if (!jsonObject.optBoolean("error")) {
                    _result.value = Pair(true, jsonObject.optString("message"))
                } else {
                    _result.value = Pair(false, jsonObject.optString("message"))
                }
            }
        }


        return result
    }

    //fun cancelAllRequests() = coroutineContext.cancel()

}