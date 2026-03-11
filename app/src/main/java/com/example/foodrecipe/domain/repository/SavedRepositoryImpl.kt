package com.example.foodrecipe.domain.repository

import com.example.foodrecipe.data.local.dao.SavedRecipeDao
import com.example.foodrecipe.data.local.entity.RecipeEntity
import com.example.foodrecipe.data.local.entity.SavedRecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedRepositoryImpl @Inject constructor(
    private val savedRecipeDao: SavedRecipeDao
) : SavedRepository {

    override suspend fun save(entity: SavedRecipeEntity) {
        savedRecipeDao.save(entity)
    }

    override suspend fun unsave(userId: String, recipeId: Int) {
        savedRecipeDao.unsave(userId, recipeId)
    }

    override fun observeIsSaved(
        userId: String,
        recipeId: Int
    ): Flow<Boolean> {
        return savedRecipeDao.observeIsSaved(userId, recipeId)
    }

    override fun observeSavedRecipes(
        userId: String
    ): Flow<List<RecipeEntity>> {
        return savedRecipeDao.observeSavedRecipes(userId)
    }

    override suspend fun clearUserSaved(userId: String) {
        savedRecipeDao.clearUserSaved(userId)
    }
}