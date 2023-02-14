package com.example.randommeal.data.databasefavorites

import com.example.data.datasource.MealFavoriteLocalDataSource
import com.example.data.datasource.MealLocalDataSource
import com.example.domain.DetailMeal
import com.example.domain.Error
import com.example.randommeal.data.databasefavorites.DetailMeal as DbDetailMeal
import com.example.randommeal.tryCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealFavoriteRoomDataSource @Inject constructor(private val mealFavoriteDao: MealFavoritesDao) :
    MealFavoriteLocalDataSource {


    override val mealsFavorite: Flow<List<DetailMeal>> = mealFavoriteDao.getAll().map { it.toDomainModel()}


    override suspend fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Flow<DetailMeal> {
        TODO("Not yet implemented")
    }

    override suspend fun size(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun nukeTable() {

    }

    override suspend fun saveFavorites(meal:  List<DetailMeal> ): Error? = tryCall {
        mealFavoriteDao.insertMeals(meal.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )
}

private fun List<DbDetailMeal>.toDomainModel(): List<DetailMeal> = map { it.toDomainModel() }

private fun DbDetailMeal.toDomainModel():  DetailMeal =  DetailMeal(
    id,
    cheap,
    cookingMinutes,
    creditsText,
    dairyFree,
    gaps,
    glutenFree,
    healthScore,
    id,
    image,
    imageType,
    instructions,
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
    vegetarian,
    veryHealthy,
    veryPopular,
    weightWatcherSmartPoints,
    favorite)

private fun List<DetailMeal>.fromDomainModel(): List<DbDetailMeal> = map { it.fromDomainModel() }

private fun DetailMeal.fromDomainModel():  DbDetailMeal =  DbDetailMeal(
    id,aggregateLikes, cheap, cookingMinutes, creditsText, dairyFree, gaps, glutenFree, healthScore, id, image, imageType, instructions, lowFodmap, preparationMinutes, pricePerServing, readyInMinutes, servings, sourceName, sourceUrl, spoonacularSourceUrl, summary, sustainable, title, vegan, vegetarian, veryHealthy, veryPopular, weightWatcherSmartPoints, favorite)



