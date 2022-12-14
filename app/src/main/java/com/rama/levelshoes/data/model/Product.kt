package com.rama.levelshoes.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorites")
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id :Int,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price: Double,

    @SerializedName("sku")
    @ColumnInfo(name = "sku")
    val sku: String,

    @SerializedName("brand")
    @ColumnInfo(name = "brand")
    val brand: String,

    @SerializedName("originalPrice")
    @ColumnInfo(name = "originalPrice")
    val originalPrice: String,

    @ColumnInfo(name = "isFav")
    val isFavourite: Boolean = false,

    val isAdded: Boolean
) : Parcelable
