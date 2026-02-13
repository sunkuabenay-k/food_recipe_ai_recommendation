package com.example.foodrecipe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: String,
    val recipeId: Int,

    val viewedAt: Long,
    val ratingGiven: Double? = null
)
