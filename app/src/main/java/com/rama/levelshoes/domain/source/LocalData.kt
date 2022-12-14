package com.rama.levelshoes.domain.source

import com.rama.levelshoes.data.model.Product


interface LocalData {
    suspend fun addToFavorites(product: Product)

    suspend fun getFavorites(): List<Product>?

    suspend fun deleteFromFavorites(id: Int)

    suspend fun clearFavorites()

    suspend fun getFavoritesNamesList(): List<String>?
}