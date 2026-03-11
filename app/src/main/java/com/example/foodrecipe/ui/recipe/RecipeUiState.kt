package com.example.foodrecipe.ui.recipe

import com.example.foodrecipe.domain.model.Recipe

sealed class RecipeUiState {

    object Loading : RecipeUiState()

    data class Success(
        val recipe: Recipe
    ) : RecipeUiState()

    data class Error(
        val message: String
    ) : RecipeUiState()
}
