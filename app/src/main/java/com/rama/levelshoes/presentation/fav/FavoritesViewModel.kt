
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.domain.usecase.GetAllFavUseCase
import com.rama.levelshoes.domain.usecase.RemoveFavUseCase
import com.rama.levelshoes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetAllFavUseCase,
    private val deleteFromFavoritesUseCase: RemoveFavUseCase
) : ViewModel() {

    private val _favoriteList = MutableLiveData<Resource<List<Product>>>()
    val favoriteList: LiveData<Resource<List<Product>>> = _favoriteList

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            _favoriteList.value = getFavoritesUseCase()
        }
    }

    fun deleteFromFavorites(id: Int) {
        viewModelScope.launch {
            deleteFromFavoritesUseCase(id)
            getFavorites()
        }
    }
}