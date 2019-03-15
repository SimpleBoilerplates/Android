package com.me.booksdemoandroid.shared.repositories

import com.me.booksdemoandroid.feature.auth.helper.AuthApi
import com.me.booksdemoandroid.feature.home.helper.HomeApi
import com.me.booksdemoandroid.shared.k.KCredential

object ApiFactory {

//    val placeholderApi : PlaceholderApi = RetrofitFactory.retrofit(AppConstants.JSON_PLACEHOLDER_BASE_URL)
//        .create(PlaceholderApi::class.java)

    val authApi = RetrofitFactory.retrofit(KCredential.Url)
        .create(AuthApi::class.java)

    val homeApi = RetrofitFactory.retrofit(KCredential.Url)
        .create(HomeApi::class.java)
}