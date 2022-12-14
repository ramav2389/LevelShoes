package com.rama.levelshoes.domain.source

import com.rama.levelshoes.data.model.data
import retrofit2.Response


interface RemoteData {
    suspend fun getProducts(): Response<data>
}