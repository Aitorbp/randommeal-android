package com.example.randommeal.data.server

import arrow.core.Either
import com.example.data.datasource.MealRemoteDataSource
import com.example.domain.DetailMeal
import com.example.domain.Error

import com.example.domain.Meal
import com.example.randommeal.di.ApiKey
import com.example.randommeal.tryCall
import javax.inject.Inject

class MealServerDataSource @Inject constructor(@ApiKey private val apiKey: String) :
    MealRemoteDataSource {


    override suspend fun findRamdomMeal(meal: String) : Either<Error, List<Meal>> = tryCall {
        RemoteConnection.service
            .listRamdomMeals(meal, 20.toString(), apiKey)
            .results
            .toDomainModel()

    }
    override suspend fun findRecipeById(id : String): Either<Error, Meal> = tryCall {
        RemoteConnection.service
            .getRecipeById(id, apiKey)
            .toDomainModel()
    }
}


private fun List<Result>.toDomainModel(): List<Meal> = map { it.toDomainModel() }

private fun Result.toDomainModel(): Meal  =
    Meal(
        id,image,imageType, title,favorite, aggregateLikes, cheap, cookingMinutes, creditsText, dairyFree, gaps, glutenFree, healthScore, instructions, lowFodmap, preparationMinutes, pricePerServing, readyInMinutes, servings, sourceName, sourceUrl, spoonacularSourceUrl, summary, sustainable, vegan, vegetarian, veryHealthy, veryPopular, weightWatcherSmartPoints
    )









