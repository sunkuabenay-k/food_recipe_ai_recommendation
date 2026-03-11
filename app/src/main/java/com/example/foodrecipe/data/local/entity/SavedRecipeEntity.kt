package com.example.foodrecipe.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "saved_recipes",
    primaryKeys = ["userId", "recipeId"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
//        ForeignKey(
//            entity = RecipeEntity::class,
//            parentColumns = ["id"],
//            childColumns = ["recipeId"],
//            onDelete = ForeignKey.CASCADE
//        )
    ]
)
data class SavedRecipeEntity(
    val userId: String,
    val recipeId: Int,
    val savedAt: Long
)
