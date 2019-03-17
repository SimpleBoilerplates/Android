package com.me.booksdemoandroid.feature.home.helper

import com.me.booksdemoandroid.shared.repositories.BaseRepository
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface HomeApi {
    @POST("/books")
    fun booksAsync(@Body jsonObject: JSONObject): Deferred<Response<String>>

}

class HomeRepository(private val api: HomeApi) : BaseRepository() {

    suspend fun books(): String? {

        val obj = JSONObject()

        return safeApiCall(
            call = { api.booksAsync(obj).await() },
            errorMessage = "Error Fetching Popular Movies"
        )

    }

}