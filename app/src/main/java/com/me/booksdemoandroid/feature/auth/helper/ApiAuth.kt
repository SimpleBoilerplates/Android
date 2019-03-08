package com.me.booksdemoandroid.feature.auth.helper

import com.me.booksdemoandroid.shared.repositories.BaseRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiAuth{


    @GET("/posts")
    fun login() : Deferred<Response<String>>


}


class LoginRepository(private val api : ApiAuth) : BaseRepository() {

    suspend fun login(): String? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)

        val loginResponse = safeApiCall(
            call = { api.login().await() },
            errorMessage = "Error Fetching Popular Movies"
        )

        return loginResponse

    }
}