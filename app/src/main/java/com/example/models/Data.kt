package com.example.models

data class Data(
    val category: Category,
    val cryptosData: List<CryptosData>,
    val stocksData: List<StocksData>
)