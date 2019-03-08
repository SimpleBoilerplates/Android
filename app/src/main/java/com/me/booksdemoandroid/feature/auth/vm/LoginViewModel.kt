package com.me.booksdemoandroid.feature.auth.vm

import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
import com.me.booksdemoandroid.feature.auth.helper.LoginRepository
import com.me.booksdemoandroid.shared.extension.Coroutines
import com.me.booksdemoandroid.shared.repositories.ApiFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel(){

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    private val repository = LoginRepository(ApiFactory.authApi)

    @UiThread
    fun  login(){

        Coroutines.ioThenMain({
            repository.login()
        }) {

            //_users.value = it
        }

//        scope.launch {
//            val popularMovies = repository.login()
//           // popularMoviesLiveData.postValue(popularMovies)
//        }

    }

    fun cancelAllRequests() = coroutineContext.cancel()

}