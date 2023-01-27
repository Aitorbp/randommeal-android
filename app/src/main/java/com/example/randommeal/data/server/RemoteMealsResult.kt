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
    val title: String
)


data class Nutrition(
    val nutrients: List<Nutrient>
)


data class Nutrient(
    val amount: Double,
    val name: String,
    val unit: String
)