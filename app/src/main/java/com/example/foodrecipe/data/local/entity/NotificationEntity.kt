package com.example.foodrecipe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(

    @PrimaryKey
    val id: Int,

    val title: String,
    val message: String,
    val isRead: Boolean,
    val createdAt: Long
)
