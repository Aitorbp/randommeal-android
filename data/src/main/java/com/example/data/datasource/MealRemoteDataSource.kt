package com.example.data.datasource
import arrow.core.Either
import com.example.domain.DetailMeal
import com.example.domain.Error
import com.example.domain.Meal

interface MealRemoteDataSource {
    suspend fun findRamdomMeal(): Either<Error, List<Meal>>
    suspend fun findRecipeById(id: String): DetailMeal
}