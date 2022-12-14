package com.rama.levelshoes.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductResponse(
    @SerializedName("title") val title: String,
    @SerializedName("currency") val currency : String,
    @SerializedName("items") val items: MutableList<Product>) : Parcelable