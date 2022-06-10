package com.example.models

data class StocksData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val currentPrice: Double,
    val logo: String,
    val name: String,
    val oneDayChange: Double,
    val symbol: String,
    val updatedAt: String
)