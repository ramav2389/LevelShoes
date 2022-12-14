package com.rama.levelshoes.di.module



import com.rama.levelshoes.data.remote.ProductService
import com.rama.levelshoes.data.remote.RemoteDataImpl
import com.rama.levelshoes.data.repository.ProductRepositoryImpl
import com.rama.levelshoes.domain.ProductRepository
import com.rama.levelshoes.domain.source.RemoteData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideDataSource(
        productService: ProductService
    ): RemoteData = RemoteDataImpl(productService)

    @Provides
    @Singleton
    fun provideProductsRepository(remoteData: RemoteData): ProductRepository = ProductRepositoryImpl(remoteData)

}