
import androidx.room.Database
import androidx.room.RoomDatabase
import com.rama.levelshoes.data.model.Product


@Database(entities = [Product::class], version = 2, exportSchema = false)
abstract class FavoritesRoomDB : RoomDatabase() {
    abstract fun productFavoriteDAO(): ProductFavoriteDAO
}