package com.example.foodrecipe.di

import android.content.Context
import androidx.room.Room
import com.example.foodrecipe.data.local.dao.RecipeDao
import com.example.foodrecipe.data.local.dao.UserDao
import com.example.foodrecipe.data.local.db.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RecipeDatabase {
        return Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            "food_recipe_db"
        ).build()
    }

    @Provides
    fun provideUserDao(
        database: RecipeDatabase
    ): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(
        database: RecipeDatabase
    ): RecipeDao {
        return database.recipeDao()
    }
}
