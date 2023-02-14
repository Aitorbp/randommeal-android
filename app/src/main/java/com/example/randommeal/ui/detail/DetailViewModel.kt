package com.example.randommeal.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.DetailMeal
import com.example.domain.Error

import com.example.usecases.FindMealByIdUseCase
import com.example.usecases.SwitchMealFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    findMealByIdUseCase: FindMealByIdUseCase,
    private val switchMealFavoriteUseCase: SwitchMealFavoriteUseCase
) : ViewModel() {

    private val mealId = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).mealId

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
                val error = findMealByIdUseCase(mealId.toString())
                _state.update { it.copy(detailMeal = error) }
        }
    }

    fun onFavoriteClicked() = runBlocking<Unit> {
        CoroutineScope(Dispatchers.IO).launch {

            _state.value.detailMeal?.let { meal ->
                val error = switchMealFavoriteUseCase(meal)
            _state.update { it.copy(error = error) }
                }
        }
    }



    data class UiState(val detailMeal: DetailMeal? = null, val error: Error? = null )
}