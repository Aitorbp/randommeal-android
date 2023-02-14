package com.example.randommeal.data.databasefavorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealFavoritesDao {

    @Query("SELECT * FROM DetailMeal")
    fun getAll(): Flow<List<DetailMeal>>

    @Query("SELECT * FROM DetailMeal WHERE id = :id")
    fun findById(id: Int): Flow<DetailMeal>

    @Query("SELECT COUNT(id) FROM DetailMeal")
    fun mealCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeals(mealFavorite: List<DetailMeal>)


}