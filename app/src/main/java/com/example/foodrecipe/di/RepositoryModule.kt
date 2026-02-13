package com.example.foodrecipe.di



import com.example.foodrecipe.data.repository.UserRepositoryImpl
import com.example.foodrecipe.domain.repository.RecipeRepository
import com.example.foodrecipe.domain.repository.RecipeRepositoryImpl
import com.example.foodrecipe.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        impl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}