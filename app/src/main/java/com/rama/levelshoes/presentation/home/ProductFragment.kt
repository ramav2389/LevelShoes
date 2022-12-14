package com.rama.levelshoes.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rama.levelshoes.R
import com.rama.levelshoes.databinding.FragmentItemListBinding
import com.rama.levelshoes.util.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_item_list) {
    private val binding by viewBinding(FragmentItemListBinding::bind)
    private val viewModel by viewModels<ProductViewModel>()

    private var columnCount = 1

    private val myProductRecyclerViewAdapter by lazy { MyProductRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyProductRecyclerViewAdapter()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        myProductRecyclerViewAdapter.onFavoriteClick = {
            if (it.isFavourite.not()) viewModel.addToFavorite(it)
            else viewModel.deleteFromFavorites(it.id)
        }
    }

    private fun initObservers() {
        with(binding) {
            with(viewModel) {
                saleProducts.observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Success -> {
                            myProductRecyclerViewAdapter.updateList(it.data)
                            progressBar.gone()
                        }
                        is Resource.Error -> {
                            progressBar.gone()
                            requireView().snack(it.throwable.message.toString())
                        }
                        is Resource.Loading ->
                            progressBar.visible()
                    }
                }
            }

        }
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}