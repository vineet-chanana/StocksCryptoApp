package com.example.models

data class Category(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val cryptos: List<String>,
    val desc: Desc,
    val name: String,
    val stocks: List<String>,
    val updatedAt: String,
    val url: String,
    var showCategoryData: Boolean = false
)