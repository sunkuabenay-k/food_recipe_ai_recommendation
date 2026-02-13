package com.example.foodrecipe.ui.home

import com.example.foodrecipe.domain.model.Recipe

sealed class HomeUiState {

    object Loading : HomeUiState()

    data class Success(
        val recipes: List<Recipe>
    ) : HomeUiState()

    data class Error(
        val message: String
    ) : HomeUiState()
}
