
import androidx.lifecycle.*
import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.data.model.ProductResponse
import com.rama.levelshoes.domain.usecase.AddToFavUseCase
import com.rama.levelshoes.domain.usecase.RemoveFavUseCase
import com.rama.levelshoes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addToFavoritesUseCase: AddToFavUseCase,
    private val deleteFromFavoritesUseCase: RemoveFavUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _crudResponse = MutableLiveData<Resource<ProductResponse>>()
    val crudResponse: LiveData<Resource<ProductResponse>> = _crudResponse

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val productModel = savedStateHandle.get<Product>("product")

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        getProduct()
        _isFavorite.value = productModel?.isFavourite
    }

    fun addToBag() {
        viewModelScope.launch {
            productModel?.let {
                _crudResponse.value = Resource.Loading
            }
        }
    }

    private fun addToFavorite(product: Product) {
        viewModelScope.launch {
            addToFavoritesUseCase(product)
        }
    }

    private fun deleteFromFavorites(id: Int) {
        viewModelScope.launch {
            deleteFromFavoritesUseCase(id)
        }
    }

    private fun getProduct() {
        productModel?.let {
            _product.value = it
        }
    }

    fun setFavoriteState() {
        productModel?.let {
            if (_isFavorite.value == true) {
                deleteFromFavorites(it.id)
                _isFavorite.value = false
            } else {
                addToFavorite(it)
                _isFavorite.value = true
            }
        }
    }
}