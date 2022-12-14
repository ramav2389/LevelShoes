package com.rama.levelshoes.data.remote

import com.rama.levelshoes.data.model.data
import retrofit2.Response
import retrofit2.http.GET


interface ProductService {
    @GET("5c138271-d8dd-4112-8fb4-3adb1b7f689e")
    suspend fun getAllProduct():Response<data>
}