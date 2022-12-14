
import com.rama.levelshoes.data.model.Product
import com.rama.levelshoes.domain.source.LocalData
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalDataImpl @Inject constructor(
    private val productFavoriteDAO: ProductFavoriteDAO,
    private val ioDispatcher: CoroutineContext
) : LocalData {

    override suspend fun addToFavorites(product: Product) = withContext(ioDispatcher) {
        productFavoriteDAO.addToFavorite(product)
    }

    override suspend fun getFavorites(): List<Product>? = withContext(ioDispatcher) {
        productFavoriteDAO.getFavorites()
    }

    override suspend fun deleteFromFavorites(id: Int) = withContext(ioDispatcher) {
        productFavoriteDAO.deleteFromFavorites(id)
    }

    override suspend fun clearFavorites() = withContext(ioDispatcher) {
        productFavoriteDAO.clearFavorites()
    }

    override suspend fun getFavoritesNamesList(): List<String>? = withContext(ioDispatcher) {
        productFavoriteDAO.getFavoritesTitles()
    }

}