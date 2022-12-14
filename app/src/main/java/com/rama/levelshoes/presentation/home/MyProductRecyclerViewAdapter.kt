package com.rama.levelshoes.presentation.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.rama.levelshoes.R
import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.databinding.FragmentItemBinding


class MyProductRecyclerViewAdapter(
) : RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder>() {

    private val list = ArrayList<Product>()
    var onFavoriteClick: (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.nameView.text = item.name
        holder.priceView.text = item.originalPrice
        holder.salePriceView.text = item.price.toString()
        Glide.with(holder.imageView).load(item.image).into(holder.imageView)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(updatedList: List<Product>) {
        list.clear()
        list.addAll(updatedList)
        notifyItemRangeInserted(0, updatedList.size)
    }

    inner class ViewHolder(private var binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.tvProductName
        val priceView: TextView = binding.tvProductPrice
        val salePriceView: TextView = binding.tvProductSale
        val imageView: ImageView = binding.imgProduct

        fun bind(item: Product) {
            with(binding) {
                imgFavorite.setOnClickListener {

                    if (item.isFavourite) {
                        onFavoriteClick(item)
                        imgFavorite.setImageResource(R.drawable.ic_fav)
                    } else {
                        onFavoriteClick(item)
                        imgFavorite.setImageResource(R.drawable.ic_fav_selected)
                    }
                }

                imgProduct.setOnClickListener {
                    it.findNavController()
                        .navigate(R.id.action_productFragment_to_productDetailFragment)

                }
            }
        }


        override fun toString(): String {
            return super.toString() + " '" + salePriceView.text + "'"
        }
    }

}