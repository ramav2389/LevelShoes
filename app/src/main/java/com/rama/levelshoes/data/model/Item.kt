package com.rama.levelshoes.data.model

data class Item(
    val badges: List<String>,
    val brand: String,
    val id: String,
    val image: String,
    val name: String,
    val originalPrice: Int,
    val price: Int,
    val sku: String
)