package com.me.booksdemoandroid.shared.repositories

import com.me.booksdemoandroid.feature.auth.helper.ApiAuth
import com.me.booksdemoandroid.shared.k.KCredential

object ApiFactory{

//    val placeholderApi : PlaceholderApi = RetrofitFactory.retrofit(AppConstants.JSON_PLACEHOLDER_BASE_URL)
//        .create(PlaceholderApi::class.java)

    val authApi = RetrofitFactory.retrofit(KCredential.Url)
        .create(ApiAuth::class.java)
}