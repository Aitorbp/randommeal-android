package com.example.randommeal.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Meal
import com.example.randommeal.R
import com.example.randommeal.databinding.ViewMealBinding
import com.example.randommeal.ui.common.basicDiffUtil
import com.example.randommeal.ui.common.inflate

class MealsAdapter (private val listener: (Meal) -> Unit) :
    ListAdapter<Meal, MealsAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_meal, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewMealBinding.bind(view)
        fun bind(meal: Meal) {
            binding.meal = meal
        }
    }
}