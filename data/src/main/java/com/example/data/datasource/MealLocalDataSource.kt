package com.example.data.datasource

import com.example.domain.DetailMeal
import com.example.domain.Error
import com.example.domain.Meal
import kotlinx.coroutines.flow.Flow

interface MealLocalDataSource {

    val meals: Flow<List<Meal>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<Meal>
    fun findByMeal(title: String): Flow<List<Meal>>
    suspend fun save(meals: List<Meal>): Error?
    suspend fun size(): Int
    suspend fun nukeTable()
}