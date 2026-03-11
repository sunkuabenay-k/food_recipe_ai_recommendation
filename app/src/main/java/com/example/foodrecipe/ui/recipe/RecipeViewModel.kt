package com.example.foodrecipe.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipe.core.network.ApiResult
import com.example.foodrecipe.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)

    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    fun loadRecipe(recipeId: Int) {

        viewModelScope.launch {

            repository.getRecipeById(recipeId).collect { result ->

                _uiState.value = when (result) {

                    is ApiResult.Loading ->
                        RecipeUiState.Loading

                    is ApiResult.Success ->
                        RecipeUiState.Success(result.data)

                    is ApiResult.Error ->
                        RecipeUiState.Error(result.message)
                }
            }
        }
    }
}
