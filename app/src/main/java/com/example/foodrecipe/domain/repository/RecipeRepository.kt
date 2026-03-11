package com.example.foodrecipe.domain.repository


import com.example.foodrecipe.core.network.ApiResult
import com.example.foodrecipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipes(): Flow<ApiResult<List<Recipe>>>

    suspend fun refreshRecipes()

    fun getRecipeById(id: Int): Flow<ApiResult<Recipe>>

}
