package com.rama.levelshoes.di.module

import FavoritesRoomDB
import ProductFavoriteDAO
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomDBModule {

    @Provides
    fun provideProductFavoriteDAO(favoritesRoomDB: FavoritesRoomDB): ProductFavoriteDAO =
        favoritesRoomDB.productFavoriteDAO()

    @Provides
    @Singleton
    fun provideFavoritesRoomDB(@ApplicationContext appContext: Context): FavoritesRoomDB =
        Room.databaseBuilder(
            appContext,
            FavoritesRoomDB::class.java,
            "favorites_database"
        ).fallbackToDestructiveMigration().build()


}