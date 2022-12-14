package com.canerture.e_commerce_app.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rama.levelshoes.databinding.ItemDetailImageBinding

class ProductDetailImagesAdapter :
    RecyclerView.Adapter<ProductDetailImagesAdapter.ProductsViewHolder>() {

    private val list = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding =
            ItemDetailImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) =
        holder.bind(list[position])

    inner class ProductsViewHolder(private var binding: ItemDetailImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.image = item
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(updatedList: List<String>) {
        list.clear()
        list.addAll(updatedList)
        notifyItemRangeInserted(0, updatedList.size)
    }
}