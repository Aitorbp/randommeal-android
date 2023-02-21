package com.example.randommeal.ui.detail

import androidx.databinding.BindingAdapter
import com.example.domain.Meal


@BindingAdapter("meal")
fun MealDetailInfoView.updateMealDetails(meal: Meal?) {
    if (meal != null) {
        setMeal(meal)
    }
}