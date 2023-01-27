package com.example.usecases

import com.example.data.MealsRepository
import javax.inject.Inject

class RequestRandomMealsUseCase @Inject constructor (private val repository: MealsRepository) {

    suspend operator fun invoke(): com.example.domain.Error? {
        return repository.requestRamdomMeals()
    }
}