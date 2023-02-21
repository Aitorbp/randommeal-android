package com.example.data

import com.example.data.datasource.MealLocalDataSource
import com.example.data.datasource.MealRemoteDataSource
import com.example.domain.Meal
import com.example.domain.Error
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealsRepository @Inject constructor(
    private val localDataSource : MealLocalDataSource,
    private val remoteDataSource : MealRemoteDataSource
) {

    val randomMeals = localDataSource.meals



    fun findById(id: Int): Flow<Meal> = localDataSource.findById(id)

    fun findByMeal(title: String): Flow<List<Meal>> = localDataSource.findByMeal(title)

    suspend fun sizeMeal(): Int = localDataSource.size()

    suspend fun requestRandomMeals(meal: String): Error? {
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

    suspend fun requestMealsById(id: String): Meal? {
            val meal = remoteDataSource.findRecipeById(id)
             return meal.fold(
                        ifLeft = { null },
                        ifRight = { it }
                    )
    }



    suspend fun switchFavoriteMeal(meal: Meal): Error? {
            val updatedMeal = meal.copy(favorite = !meal.favorite!!)
           // val randomMeals = remoteDataSource.meals
            return localDataSource.save(listOf(updatedMeal))
    }
}