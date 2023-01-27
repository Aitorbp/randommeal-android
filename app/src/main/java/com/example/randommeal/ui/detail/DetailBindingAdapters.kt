package com.example.randommeal.ui.detail

import androidx.databinding.BindingAdapter
import com.example.domain.DetailMeal


@BindingAdapter("meal")
fun MealDetailInfoView.updateMealDetails(meal: DetailMeal?) {
    if (meal != null) {
        setMeal(meal)
    }
}