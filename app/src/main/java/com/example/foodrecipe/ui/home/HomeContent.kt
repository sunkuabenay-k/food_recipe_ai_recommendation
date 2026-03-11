package com.example.foodrecipe.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodrecipe.domain.model.Recipe
import com.example.foodrecipe.ui.components.HorizontalRecipeCard
import com.example.foodrecipe.ui.components.NewRecipeCard
import kotlin.collections.take

@Composable
fun HomeContent(
    recipes: List<Recipe>,
    onRecipeClick: (Int) -> Unit
) {
    // Use ONE LazyColumn for the whole screen
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        // 1. Header: Popular Recipes
        item {
            Text(
                text = "Popular Recipes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 2. Horizontal Scroll (Nest LazyRow inside item)
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(recipes) { recipe ->
                    HorizontalRecipeCard(
                        recipe = recipe,
                        onClick = { onRecipeClick(recipe.id) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        // 3. Header: New Recipes
        item {
            Text(
                text = "New Recipes",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 4. Vertical List Items
        // Note: items() here is part of the parent LazyColumn
        items(recipes.take(5)) { recipe ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                NewRecipeCard(
                    recipe = recipe,
                    onClick = { onRecipeClick(recipe.id) }
                )
            }
        }
    }
}