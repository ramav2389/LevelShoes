package com.rama.levelshoes.data.repository

import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.domain.ProductRepository
import com.rama.levelshoes.domain.source.RemoteData
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(private val remoteData: RemoteData) :
ProductRepository{
    override suspend fun getProducts(): List<Product> {
        val productList = arrayListOf<Product>()
        remoteData.getProducts().apply {

        }
////        val favoriteNamesList = localData.getFavoritesNamesList().orEmpty()
//        remoteData.getProducts().items
//            .forEach{ product ->
////            productList.add(
////               Product(product.id,
////                   product.name,
////                   product.image,
////                   product.price,
////                   product.sku,
////                   product.brand,
////                   product.originalPrice,
////                   false
////            ))
//        }
        return productList
    }

//    override suspend fun addToFavorites(product: Product) {
//        localData.addToFavorites(product)
//    }
//
//    override suspend fun getFavorites(): List<Product>? {
//        val favoriteList = arrayListOf<Product>()
//        localData.getFavorites()?.forEach { product ->
//            favoriteList.add(
//                Product(
//                    product.id,
//                    product.name,
//                    product.image,
//                    product.price,
//                    product.sku,
//                    product.brand,
//                    product.originalPrice,
//                    isFavourite = true
//                )
//            )
//        }
//        return favoriteList
//    }
//
//    override suspend fun deleteFromFavorites(id: Int) {
//        localData.deleteFromFavorites(id)
//    }
//
//    override suspend fun clearFavorites() {
//        localData.clearFavorites()
//    }
//
//    override suspend fun getFavoritesNamesList(): List<String>? {
//      return localData.getFavoritesNamesList()
//    }
}