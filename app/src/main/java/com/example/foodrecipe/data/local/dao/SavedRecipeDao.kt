package com.example.foodrecipe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodrecipe.data.local.entity.RecipeEntity
import com.example.foodrecipe.data.local.entity.SavedRecipeEntity

@Dao
interface SavedRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(savedRecipe: SavedRecipeEntity)

    @Query("""
        DELETE FROM saved_recipes 
        WHERE userId = :userId AND recipeId = :recipeId
    """)
    suspend fun unsave(userId: String, recipeId: Int)

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM saved_recipes
            WHERE userId = :userId AND recipeId = :recipeId
        )
    """)
    suspend fun isSaved(userId: String, recipeId: Int): Boolean

    @Query("""
        SELECT r.* FROM recipes r
        INNER JOIN saved_recipes s
        ON r.id = s.recipeId
        WHERE s.userId = :userId
        ORDER BY s.savedAt DESC
    """)
    suspend fun getSavedRecipes(userId: String): List<RecipeEntity>

    @Query("DELETE FROM saved_recipes WHERE userId = :userId")
    suspend fun clearUserSaved(userId: String)
}
