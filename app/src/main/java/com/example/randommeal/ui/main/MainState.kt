package com.example.randommeal.ui.main

import android.Manifest
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.domain.Error
import com.example.domain.Meal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.randommeal.R
fun Fragment.buildMainState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),

) = MainState(context, scope, navController)

class MainState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController
) {
    fun onMovieClicked(movie: Meal) {
        val action = MainFragmentDirections.actionMainToDetail(movie.id!!)
        navController.navigate(action)
    }


    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }

}