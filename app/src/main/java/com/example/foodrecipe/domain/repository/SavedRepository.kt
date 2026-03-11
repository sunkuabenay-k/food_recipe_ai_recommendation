package com.example.foodrecipe.domain.repository

import com.example.foodrecipe.data.local.entity.RecipeEntity
import com.example.foodrecipe.data.local.entity.SavedRecipeEntity
import kotlinx.coroutines.flow.Flow

interface SavedRepository {

    suspend fun save(entity: SavedRecipeEntity)

    suspend fun unsave(userId: String, recipeId: Int)

    fun observeIsSaved(
        userId: String,
        recipeId: Int
    ): Flow<Boolean>

    fun observeSavedRecipes(
        userId: String
    ): Flow<List<RecipeEntity>>

    suspend fun clearUserSaved(userId: String)
}