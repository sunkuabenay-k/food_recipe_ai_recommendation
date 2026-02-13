package com.example.foodrecipe.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipe.core.network.ApiResult
import com.example.foodrecipe.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            repository.getRecipes().collect { result ->
                _uiState.value = when (result) {

                    is ApiResult.Loading ->
                        HomeUiState.Loading

                    is ApiResult.Success ->
                        HomeUiState.Success(result.data)

                    is ApiResult.Error ->
                        HomeUiState.Error(result.message)
                }
            }
        }
    }
}
