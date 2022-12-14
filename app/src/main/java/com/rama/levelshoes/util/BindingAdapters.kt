package com.rama.levelshoes.util

import android.graphics.Paint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.rama.levelshoes.R

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView).load(imageUrl).into(imageView)
}

@BindingAdapter("favoriteState")
fun favoriteState(imageView: ImageView, isFavorite: Boolean) {
    if (isFavorite) imageView.setImageResource(R.drawable.ic_fav_selected)
    else imageView.setImageResource(R.drawable.ic_fav)
}

@BindingAdapter("priceValue", "originalPriceValue")
fun priceText(
    tvPrice: MaterialTextView,
    priceValue: Double?,
    salePrice: Double?
) {
    if (salePrice != null) {
        tvPrice.text = String.format("%.3f₺", priceValue)
        tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        tvPrice.alpha = 0.5f
    } else {
        tvPrice.text = String.format("%.3f₺", priceValue)
    }
}

@BindingAdapter("saleTextPrice")
fun saleText(
    tvSale: MaterialTextView,
    saleTextPrice: Double?
) {
    if (saleTextPrice != null) {
        tvSale.text = String.format("%.3f₺", saleTextPrice)
    } else {
        tvSale.gone()
    }
}
