package com.example.foodrecipe.data.remote.api


import com.example.foodrecipe.data.remote.dto.RecipeResponseDto
import com.example.foodrecipe.data.remote.dto.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes")
    suspend fun getRecipes(
        @Query("limit") limit: Int = 30,
        @Query("skip") skip: Int = 0
    ): RecipeResponseDto

    @GET("recipes/{id}")
    suspend fun getRecipeById(
        @Path("id") id: Int
    ): RecipeDto

    @GET("recipes/search")
    suspend fun searchRecipes(
        @Query("q") query: String
    ): RecipeResponseDto
}
