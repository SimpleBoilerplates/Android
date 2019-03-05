package com.me.booksdemoandroid.shared.extension

fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}


inline fun <T : Any, R> whenNotNull(input: T?, callback: (T) -> R): R? {
    return input?.let(callback)
}