package com.example.usecases

import com.example.data.MealsRepository
import javax.inject.Inject

class FindMealByIdUseCase @Inject constructor(private val repository: MealsRepository) {

    suspend operator fun invoke(id: String)  = repository.requestMealsById(id)

}