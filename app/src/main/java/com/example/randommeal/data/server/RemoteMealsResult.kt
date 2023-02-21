package com.example.randommeal.data.server

data class RemoteMealsResult(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)


data class Result(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String,
    val favorite: Boolean,
    val aggregateLikes: Int,
    val cheap: Boolean,
    val cookingMinutes: Int,
    val creditsText: String,
    val dairyFree: Boolean,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Int,
    val instructions: String,
    val lowFodmap: Boolean,
    val preparationMinutes: Int,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int
)


data class Nutrition(
    val nutrients: List<Nutrient>
)


data class Nutrient(
    val amount: Double,
    val name: String,
    val unit: String
)