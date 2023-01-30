package com.example.usecases

import com.example.data.MealsRepository
import com.example.domain.Error
import com.example.domain.Meal
import javax.inject.Inject

class RequestRandomMealsUseCase @Inject constructor (private val repository: MealsRepository) {

    suspend operator fun invoke(meal: String) : Error? {
        return repository.requestRamdomMeals(meal)
    }
}