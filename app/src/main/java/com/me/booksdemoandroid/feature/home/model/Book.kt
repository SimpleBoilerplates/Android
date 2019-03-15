package com.me.booksdemoandroid.feature.home.model

data class Book(
    val createdAt: String,
    val description: String,
    val id: Int,
    val preview: String,
    val subTitle: String,
    val title: String,
    val updatedAt: String
)