package com.example.randommeal.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM Meal")
    fun getAll(): Flow<List<Meal>>

    @Query("SELECT * FROM Meal WHERE id = :id")
    fun findById(id: Int): Flow<Meal>

    @Query("SELECT * FROM Meal WHERE title = :title")
    fun findByMeal(title: String): Flow<List<Meal>>

    @Query("SELECT COUNT(id) FROM Meal")
    fun mealCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeals(meals: List<Meal>)

    @Query("DELETE FROM Meal")
    fun nukeTable()

}