package com.example.randommeal.data.databasefavorites

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.randommeal.data.database.Meal
import com.example.randommeal.data.database.MealDao

@Database(entities = [DetailMeal::class], version = 2, exportSchema = false)
abstract class MealFavoritesDatabase : RoomDatabase() {
    abstract fun mealFavoritesDao(): MealFavoritesDao
}