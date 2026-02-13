package com.example.foodrecipe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(

    @PrimaryKey
    val id: Int,

    val name: String,
    val image: String,

    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,

    val difficulty: String,
    val cuisine: String,

    val caloriesPerServing: Int,

    val rating: Double,
    val reviewCount: Int,

    val ingredients: List<String>,
    val instructions: List<String>,
    val tags: List<String>,
    val mealType: List<String>,

    val lastUpdated: Long
)
