package com.example.usecases

import com.example.data.MealsRepository
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(private val repository: MealsRepository) {

    operator fun invoke() = repository.randomMeals
}