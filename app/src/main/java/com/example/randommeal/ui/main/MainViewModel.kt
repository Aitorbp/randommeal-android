package com.example.randommeal.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Meal
import com.example.randommeal.toError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.usecases.*
import kotlinx.coroutines.flow.*

@HiltViewModel
class MainViewModel @Inject constructor(getMealsUseCase: GetMealsUseCase,
                                        private val requestRandomMealsUseCase: RequestRandomMealsUseCase

) :
    ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()


    init {

        viewModelScope.launch {
            getMealsUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { meals -> _state.update { UiState(meals = meals) } }
        }
    }

    fun onUiReady() {
        CoroutineScope(Dispatchers.IO).launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestRandomMealsUseCase("pasta")
            _state.update { _state.value.copy(loading = false, error = error) }
        }
    }

    fun filterMovie(filter: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val error = requestRandomMealsUseCase(filter.toString())
            _state.update { _state.value.copy(loading = false, error = error) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val meals: List<Meal>? = null,
        val error: com.example.domain.Error? = null
    )
}