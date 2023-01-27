package com.example.randommeal.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.DetailMeal
import com.example.domain.Error

import com.example.usecases.FindMealByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    findMealByIdUseCase: FindMealByIdUseCase,
) : ViewModel() {

    private val mealId = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).mealId

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
                val error = findMealByIdUseCase(mealId.toString())
                _state.update { it.copy(meal = error) }
          }
    }



    data class UiState(val meal: DetailMeal? = null, val error: Error? = null)
}