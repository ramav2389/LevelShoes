


import com.rama.levelshoes.data.remote.ProductService
import com.rama.levelshoes.data.remote.RemoteDataImpl
import com.rama.levelshoes.domain.source.LocalData
import com.rama.levelshoes.domain.source.RemoteData
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(SingletonComponent::class)
 object DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(
        productService: ProductService
    ): RemoteData = RemoteDataImpl(productService)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        productFavoriteDAO: ProductFavoriteDAO,
        ioDispatcher: CoroutineContext
    ): LocalData = LocalDataImpl(productFavoriteDAO, ioDispatcher)

}