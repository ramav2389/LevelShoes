package com.rama.levelshoes.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.domain.usecase.AddToFavUseCase
import com.rama.levelshoes.domain.usecase.GetAllProductsUseCase
import com.rama.levelshoes.domain.usecase.RemoveFavUseCase
import com.rama.levelshoes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductUseCase: GetAllProductsUseCase,
    private val addToFavUseCase: AddToFavUseCase,
    private val deleteFromFavUseCase: RemoveFavUseCase
    ) : ViewModel() {

    private val _saleProducts = MutableLiveData<Resource<List<Product>>>(Resource.Loading)
    val saleProducts: MutableLiveData<Resource<List<Product>>> = _saleProducts

    init {
        getAllProducts()
    }

    fun getAllProducts(){
        viewModelScope.launch {
            _saleProducts.value = getAllProductUseCase()
        }
    }

    fun addToFavorite(product: Product) {
        viewModelScope.launch {
            addToFavUseCase(product)
        }
    }

    fun deleteFromFavorites(id: Int) {
        viewModelScope.launch {
            deleteFromFavUseCase(id)
        }
    }

}