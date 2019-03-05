package com.me.booksdemoandroid.shared.k

class KEnum {
    companion object {

        enum class LoadingType(val value: Int) {
            DownloadingBook(0), Loading(1), NoConnection(2), Error(3)
        }

        enum class SharedPref {
            LastReading, DevicesAddress, Token, ExpiresIn, LoginTime, Me
        }

        enum class BroadCast {
            LastReading
        }

    }
}