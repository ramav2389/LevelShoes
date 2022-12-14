package com.rama.levelshoes.domain.usecase

import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.domain.ProductRepository
import javax.inject.Inject


class AddToFavUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) =
        productRepository.addToFavorites(product)
}