package com.example.randommeal.di

import android.app.Application
import androidx.room.Room
import com.example.data.datasource.MealFavoriteLocalDataSource
import com.example.data.datasource.MealLocalDataSource
import com.example.data.datasource.MealRemoteDataSource
import com.example.randommeal.R
import com.example.randommeal.data.database.MealDatabase
import com.example.randommeal.data.database.MealRoomDataSource
import com.example.randommeal.data.databasefavorites.MealFavoriteRoomDataSource
import com.example.randommeal.data.databasefavorites.MealFavoritesDatabase
import com.example.randommeal.data.server.MealServerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)


    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        MealDatabase::class.java,
        "meal-db" )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMealDao(db: MealDatabase) = db.mealDao()

    @Provides
    @Singleton
    fun provideMealFavoriteDatabase(app: Application) = Room.databaseBuilder(
        app,
        MealFavoritesDatabase::class.java,
        "mealFavorite-db" )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMealFavoriteDao(db: MealFavoritesDatabase) = db.mealFavoritesDao()


}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: MealRoomDataSource): MealLocalDataSource

    @Binds
    abstract fun bindLocalFavoriteDataSource(localFavoriteDataSource: MealFavoriteRoomDataSource): MealFavoriteLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: MealServerDataSource): MealRemoteDataSource

}