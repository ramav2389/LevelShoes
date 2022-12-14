package com.rama.levelshoes.domain

import com.rama.levelshoes.data.model.Product


interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun addToFavorites(product: Product)

    suspend fun getFavorites(): List<Product>?

    suspend fun deleteFromFavorites(id: Int)

    suspend fun clearFavorites()

    suspend fun getFavoritesNamesList(): List<String>?
}