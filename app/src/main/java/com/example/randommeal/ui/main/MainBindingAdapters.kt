package com.example.randommeal.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Meal


@BindingAdapter("items")
fun RecyclerView.setItems(movies: List<Meal>?) {
    if (movies != null) {
        (adapter as? MealsAdapter)?.submitList(movies)
    }
}