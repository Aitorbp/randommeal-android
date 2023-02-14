package com.example.data.datasource

import com.example.domain.DetailMeal
import com.example.domain.Error
import com.example.domain.Meal
import kotlinx.coroutines.flow.Flow

interface MealFavoriteLocalDataSource {

    val mealsFavorite: Flow<List<DetailMeal>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<DetailMeal>

    suspend fun size(): Int
    suspend fun nukeTable()
    suspend fun saveFavorites(mealDetail:  List<DetailMeal>): Error?
}