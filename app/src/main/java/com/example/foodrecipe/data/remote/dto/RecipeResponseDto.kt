package com.example.foodrecipe.data.remote.dto

data class RecipeResponseDto(
    val recipes: List<RecipeDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
