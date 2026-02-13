package com.example.foodrecipe.domain.repository

import com.example.foodrecipe.core.network.ApiResult
import com.example.foodrecipe.core.network.ErrorMapper
import com.example.foodrecipe.data.local.dao.RecipeDao
import com.example.foodrecipe.data.mapper.RecipeMapper.toDomain
import com.example.foodrecipe.data.mapper.RecipeMapper.toEntity
import com.example.foodrecipe.data.remote.api.RecipeApi
import com.example.foodrecipe.domain.model.Recipe
import com.example.foodrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: RecipeApi,
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override fun getRecipes(): Flow<ApiResult<List<Recipe>>> = flow {

        emit(ApiResult.Loading)

        try {
            // 1️⃣ Load from local first
            val localData = recipeDao.getAll()
            if (localData.isNotEmpty()) {
                emit(ApiResult.Success(localData.map { it.toDomain() }))
            }

            // 2️⃣ Fetch from API
            val response = api.getRecipes()

            val entities = response.recipes.map { it.toEntity() }

            // 3️⃣ Cache in Room
            recipeDao.clear()
            recipeDao.insertAll(entities)

            // 4️⃣ Emit updated data
            val updatedData = recipeDao.getAll()
            emit(ApiResult.Success(updatedData.map { it.toDomain() }))

        } catch (e: Exception) {

            val localData = recipeDao.getAll()

            if (localData.isNotEmpty()) {
                emit(ApiResult.Success(localData.map { it.toDomain() }))
            } else {
                emit(ErrorMapper.map(e))
            }
        }
    }

    override suspend fun refreshRecipes() {
        val response = api.getRecipes()
        val entities = response.recipes.map { it.toEntity() }
        recipeDao.clear()
        recipeDao.insertAll(entities)
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return recipeDao.getById(id)?.toDomain()
    }
}
