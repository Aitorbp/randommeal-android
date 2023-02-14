package com.example.usecases

import com.example.data.MealsRepository
import com.example.domain.DetailMeal
import com.example.domain.Error
import javax.inject.Inject

class   SwitchMealFavoriteUseCase  @Inject constructor(private val repository: MealsRepository) {

    suspend operator fun invoke(meal: DetailMeal): Error? = repository.switchFavoriteMeal(meal)
}
