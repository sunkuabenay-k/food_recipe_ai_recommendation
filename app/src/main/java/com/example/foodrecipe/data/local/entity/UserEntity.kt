package com.example.foodrecipe.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)] // Ensures DB rejects duplicates
)
data class UserEntity(
    @PrimaryKey
    val userId: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val password: String,
    val createdAt: Long,
    val isLoggedIn: Boolean = false,


    val profileImage: String? = null,
    val bio: String? = null,
    val dietaryPreferences: String? = null
)