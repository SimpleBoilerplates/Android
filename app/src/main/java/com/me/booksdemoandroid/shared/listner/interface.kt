package com.me.booksdemoandroid.shared.listner

interface OnBackPressedListener {
    fun doBack()
    fun doBackWithStart()
}

interface OnItemClickListener {
    fun itemClicked(position: Int)
}