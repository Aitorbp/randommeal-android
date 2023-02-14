package com.example.randommeal.data.database

import com.example.data.datasource.MealLocalDataSource
import com.example.domain.Meal
import com.example.domain.DetailMeal
import com.example.domain.Error
import com.example.randommeal.tryCall
import com.example.randommeal.data.database.Meal as DbMeal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject



class MealRoomDataSource @Inject constructor(private val mealDao: MealDao) :
    MealLocalDataSource {

    override val meals: Flow<List<Meal>> = mealDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = mealDao.mealCount() == 0

    override fun findById(id: Int): Flow<Meal> = mealDao.findById(id).map { it.toDomainModel() }

    override fun findByMeal(title: String): Flow<List<Meal>> =
        mealDao.findByMeal(title).map { it.toDomainModel() }

    override suspend fun save(meals: List<Meal>):  Error? = tryCall {
        mealDao.insertMeals(meals.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun size(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun nukeTable() {
        mealDao.nukeTable()
    }

}

private fun List<DbMeal>.toDomainModel(): List<Meal> = map { it.toDomainModel() }

private fun DbMeal.toDomainModel(): Meal = Meal(
    id,
    image,
    imageType,
    title,
    favorite)

private fun List<Meal>.fromDomainModel(): List<DbMeal> = map { it.fromDomainModel() }

private fun Meal.fromDomainModel(): DbMeal = DbMeal (
    id,
    id,
    image,
    imageType,
    title,
    favorite)

