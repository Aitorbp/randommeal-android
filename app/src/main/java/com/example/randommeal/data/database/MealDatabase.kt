package com.example.randommeal.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Meal::class], version = 4, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}






