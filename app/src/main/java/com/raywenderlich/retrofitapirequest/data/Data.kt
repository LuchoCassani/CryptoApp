package com.raywenderlich.retrofitapirequest.data



data class Data(
    val name: String,
    val id: Int,
    val quote: Quote,
    val slug: String,
    val symbol: String,
    val tags: List<String>,
    val total_supply: Double
)