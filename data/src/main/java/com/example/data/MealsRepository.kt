package com.example.data

import arrow.core.Either
import com.example.data.datasource.MealLocalDataSource
import com.example.data.datasource.MealRemoteDataSource
import com.example.domain.DetailMeal
import com.example.domain.Meal
import com.example.domain.Error
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealsRepository @Inject constructor(
    private val localDataSource : MealLocalDataSource,
    private val remoteDataSource : MealRemoteDataSource
) {

    val ramdomMeals = localDataSource.meals

    fun findById(id: Int): Flow<Meal> = localDataSource.findById(id)

    fun findByMeal(title: String): Flow<List<Meal>> = localDataSource.findByMeal(title)

    suspend fun sizeMeal(): Int = localDataSource.size()

    suspend fun requestRamdomMeals(meal: String): Error? {
        if (localDataSource.isEmpty() || meal.isEmpty() || meal.isBlank() ) {
            val meals = remoteDataSource.findRamdomMeal(meal)
            meals.fold(ifLeft = { return it }) {
                localDataSource.nukeTable()
                localDataSource.save(it)
            }
        }
        else {
            val meals = remoteDataSource.findRamdomMeal(meal)
            meals.fold(ifLeft = { return it }) {
                localDataSource.nukeTable()
                localDataSource.save(it)
            }
        }
        return null
    }

    suspend fun requestMealsById(id: String): DetailMeal? {
            val meal = remoteDataSource.findRecipeById(id)
            meal.fold(
                        ifLeft = {return null },
                        ifRight = {return it }
                    )
    }


    /*
    suspend fun switchFavoriteMeal(meal: Meal): Error? {
        val updatedMovie = meal.copy(favorite = !meal.favorite)
        return localDataSource.save(listOf(updatedMovie))
    }
    */





}