package com.me.booksdemoandroid.feature.auth.helper

import com.me.booksdemoandroid.shared.repositories.BaseRepository
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/login")
    fun loginAsync(@Body jsonObject: JSONObject): Deferred<Response<String>>

    @POST("/signup")
    fun signUpAsync(@Body jsonObject: JSONObject): Deferred<Response<String>>

}


class AuthRepository(private val api: AuthApi) : BaseRepository() {

    suspend fun login(email: String, password: String): String? {

        val obj = JSONObject()
        obj.put("email", email)
        obj.put("password", password)

        return safeApiCall(
            call = { api.loginAsync(obj).await() },
            errorMessage = "Error Fetching Popular Movies"
        )

    }

    suspend fun signUp(name: String, email: String, password: String): String? {

        val obj = JSONObject()
        obj.put("name", name)
        obj.put("email", email)
        obj.put("password", password)

        return safeApiCall(
            call = { api.signUpAsync(obj).await() },
            errorMessage = "Error Fetching Popular Movies"
        )

    }
}