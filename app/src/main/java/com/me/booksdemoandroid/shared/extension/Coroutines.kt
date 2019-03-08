package com.me.booksdemoandroid.shared.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutines {

    fun <T: Any> io(work: suspend (() -> T?)): Job =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    fun <T: Any> ioThenMain(work: suspend (() -> T?), callback: ((T?) -> Unit)? = null): Job =
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async {
                return@async work()
            }.await()
            callback?.let {
                it(data)
            }
        }

}