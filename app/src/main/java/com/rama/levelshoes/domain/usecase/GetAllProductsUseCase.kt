package com.rama.levelshoes.domain.usecase

import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.domain.ProductRepository
import javax.inject.Inject
import com.rama.levelshoes.util.Resource
import retrofit2.HttpException
import java.io.IOException


class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        return try {
            Resource.Loading
            Resource.Success(productRepository.getProducts())
        } catch (e: HttpException){
            Resource.Error(e)
        } catch (e: IOException){
            Resource.Error(e)
        }
    }
}