package com.me.booksdemoandroid.feature.auth.vm

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.me.booksdemoandroid.feature.auth.helper.AuthRepository
import com.me.booksdemoandroid.shared.extension.Coroutines
import com.me.booksdemoandroid.shared.repositories.ApiFactory
import com.me.booksdemoandroid.shared.repositories.Resource
import org.json.JSONObject


class AuthViewModel : ViewModel() {

    private val repository = AuthRepository(ApiFactory.authApi)

    //private lateinit var result: MutableLiveData<Pair<Boolean,String>>

    private val _result = MutableLiveData<Resource<String>>()
    val result: LiveData<Resource<String>> get() = _result

    @UiThread
    fun login(email: String, password: String) {
        // _loading.value = true
        Coroutines.ioThenMain({
            repository.login(email, password)
        }) {
            it?.let {
                //_loading.value = false
                val jsonObject = JSONObject(it)
                if (!jsonObject.optBoolean("error")) {
                    _result.value = Resource.success(jsonObject.optString("token"))
                } else {
                    _result.value = Resource.error(jsonObject.optString("message"), null)
                }
            }
        }
    }

    @UiThread
    fun signUp(name: String, email: String, password: String) {
        // _loading.value = true
        Coroutines.ioThenMain({
            repository.signUp(name, email, password)
        }) {
            it?.let {
                //_loading.value = false
                //result = MutableLiveData()
                val jsonObject = JSONObject(it)
                if (!jsonObject.optBoolean("error")) {
                    _result.value = Resource.success(jsonObject.optString("message"))
                } else {
                    _result.value = Resource.error(jsonObject.optString("message"), null)
                }
            }
        }
        // return result
    }
}