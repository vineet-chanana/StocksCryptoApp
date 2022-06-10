package com.example.models

data class APIResponse(
    val `data`: List<Category>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)