package com.rama.levelshoes.domain.usecase

import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.domain.ProductRepository
import com.rama.levelshoes.util.Resource
import javax.inject.Inject


class GetAllFavUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Resource<List<Product>> {
          return  Resource.Success(productRepository.getFavorites().orEmpty())
    }
}