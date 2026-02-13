package com.example.foodrecipe.data.mapper

import com.example.foodrecipe.data.local.entity.RecipeEntity
import com.example.foodrecipe.data.remote.dto.RecipeDto
import com.example.foodrecipe.domain.model.Recipe

object RecipeMapper {

    fun RecipeDto.toEntity(): RecipeEntity {
        return RecipeEntity(
            id = id,
            name = name,
            image = image,
            prepTimeMinutes = prepTimeMinutes,
            cookTimeMinutes = cookTimeMinutes,
            servings = servings,
            difficulty = difficulty,
            cuisine = cuisine,
            caloriesPerServing = caloriesPerServing,
            rating = rating,
            reviewCount = reviewCount,
            ingredients = ingredients,
            instructions = instructions,
            tags = tags,
            mealType = mealType,
            lastUpdated = System.currentTimeMillis()
        )
    }

    fun RecipeEntity.toDomain(): Recipe {
        return Recipe(
            id = id,
            name = name,
            image = image,
            prepTimeMinutes = prepTimeMinutes,
            cookTimeMinutes = cookTimeMinutes,
            servings = servings,
            difficulty = difficulty,
            cuisine = cuisine,
            caloriesPerServing = caloriesPerServing,
            rating = rating,
            reviewCount = reviewCount,
            ingredients = ingredients,
            instructions = instructions,
            tags = tags,
            mealType = mealType
        )
    }
}
