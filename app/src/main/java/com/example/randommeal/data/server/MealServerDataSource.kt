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
    override suspend fun findRecipeById(id : String): Either<Error, DetailMeal> = tryCall {
        RemoteConnection.service
            .getRecipeById(id, apiKey)
            .toDomainDetailModel()
    }
}



private fun List<RemoteDetailMealResult>.toDomainDetailModel(): List<DetailMeal> = map {it.toDomainDetailModel()}

private fun RemoteDetailMealResult.toDomainDetailModel(): DetailMeal  =
    DetailMeal(
 aggregateLikes,
 cheap,
 cookingMinutes,
 creditsText,
 dairyFree,
 gaps,
 glutenFree,
 healthScore,
 id ,
 image,
 imageType,
 instructions  ,
 lowFodmap,
 preparationMinutes,
 pricePerServing,
 readyInMinutes,
 servings,
 sourceName,
 sourceUrl,
 spoonacularSourceUrl,
 summary,
 sustainable,
 title,
 vegan,
 vegetarian ,
 veryHealthy,
 veryPopular,
 weightWatcherSmartPoints,
        false
    )

private fun List<Result>.toDomainModel(): List<Meal> = map { it.toDomainModel() }

private fun Result.toDomainModel(): Meal  =
    Meal(
        id,
        image,
        imageType,
        title,
        false
    )









