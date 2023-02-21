package com.example.randommeal.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true) val idDDBB: Int?,
    val id: Int?,
    val image: String?,
    val imageType: String?,
    val title: String?,
    val favorite: Boolean?,
    val aggregateLikes: Int?,
    val cheap: Boolean?,
    val cookingMinutes: Int?,
    val creditsText: String?,
    val dairyFree: Boolean?,
    val gaps: String?,
    val glutenFree: Boolean?,
    val healthScore: Int?,
    val instructions: String?,
    val lowFodmap: Boolean?,
    val preparationMinutes: Int?,
    val pricePerServing: Double?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceName: String?,
    val sourceUrl: String?,
    val spoonacularSourceUrl: String?,
    val summary: String?,
    val sustainable: Boolean?,

    val vegan: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val weightWatcherSmartPoints: Int?,

) : Parcelable

