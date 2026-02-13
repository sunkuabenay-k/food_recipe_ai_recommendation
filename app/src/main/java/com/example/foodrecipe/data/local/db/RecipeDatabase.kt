package com.example.foodrecipe.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipe.data.local.dao.HistoryDao
import com.example.foodrecipe.data.local.dao.NotificationDao
import com.example.foodrecipe.data.local.dao.RecipeDao
import com.example.foodrecipe.data.local.dao.SavedRecipeDao
import com.example.foodrecipe.data.local.dao.UserDao
import com.example.foodrecipe.data.local.entity.HistoryEntity
import com.example.foodrecipe.data.local.entity.NotificationEntity
import com.example.foodrecipe.data.local.entity.RecipeEntity
import com.example.foodrecipe.data.local.entity.SavedRecipeEntity
import com.example.foodrecipe.data.local.entity.UserEntity

@Database(
    entities = [
        RecipeEntity::class,
        UserEntity::class,
        SavedRecipeEntity::class,
        HistoryEntity::class,
        NotificationEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun userDao(): UserDao
    abstract fun savedRecipeDao(): SavedRecipeDao
    abstract fun historyDao(): HistoryDao
    abstract fun notificationDao(): NotificationDao
}
