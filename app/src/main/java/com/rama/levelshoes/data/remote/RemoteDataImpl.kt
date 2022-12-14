package com.rama.levelshoes.data.remote

import com.rama.levelshoes.data.model.ProductResponse
import com.rama.levelshoes.data.model.data
import com.rama.levelshoes.domain.source.RemoteData
import retrofit2.Response
import javax.inject.Inject

class RemoteDataImpl @Inject constructor(
    private val productService: ProductService
) : RemoteData {

    override suspend fun getProducts(): Response<data> =
        productService.getAllProduct()
}