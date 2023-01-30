package com.example.randommeal.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randommeal.R
import com.example.randommeal.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.randommeal.ui.common.launchAndCollect
import com.example.randommeal.ui.common.textChanges
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MainFragment :  Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var mainState: MainState

    private val adapter = MealsAdapter { mainState.onMovieClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = buildMainState()

        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter

            val layoutManager = recycler.layoutManager as GridLayoutManager

        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.meals = it.meals
            binding.error = it.error?.let(mainState::errorToString)
        }
        binding.searchButton.textChanges()
            .distinctUntilChanged()
            .debounce(1500) // espere 1500ms sin eventos para emitir el valor
            .map { text -> text.toString() }
            .onEach { filter ->
                viewModel.filterMovie(filter)
            }// convertimos el CharSequence a String
            .distinctUntilChanged() // evitamos hacer búsquedas repetidas
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.onUiReady()


    }
}