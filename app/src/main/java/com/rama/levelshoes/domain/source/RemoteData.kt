package com.rama.levelshoes.domain.source

import com.rama.levelshoes.data.model.data


interface RemoteData {
    suspend fun getProducts(): data
}