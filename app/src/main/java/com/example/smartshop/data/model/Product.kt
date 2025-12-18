package com.example.smartshop.data.model

data class Product(
    val id: String = "",
    val name: String,
    val quantity: Int,
    val price: Double,
    val imageUrl: String? = null
)
