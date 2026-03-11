package com.example.foodrecipe.ui.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodrecipe.core.navigation.Screen
import com.example.foodrecipe.ui.components.FavoriteRecipeCard

@Composable
fun SavedScreen(
    navController: NavController,
    savedViewModel: SavedViewModel = hiltViewModel()
) {

    val savedRecipes by savedViewModel.savedRecipes.collectAsState()

    LaunchedEffect(Unit) {
        savedViewModel.observeSavedRecipes()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Saved recipes",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (savedRecipes.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No saved recipes yet")
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(savedRecipes) { recipe ->

                    FavoriteRecipeCard(
                        recipe = recipe,
                        isSaved = true,
                        onSaveClick = {
                            savedViewModel.toggleSave(recipe.id)
                        },
                        onClick = {
                            navController.navigate(
                                Screen.RecipeDetails.createRoute(recipe.id)
                            )
                        }
                    )
                }
            }
        }
    }
}