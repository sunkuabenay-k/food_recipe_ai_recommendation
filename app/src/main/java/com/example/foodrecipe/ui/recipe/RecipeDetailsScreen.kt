package com.example.foodrecipe.ui.recipe

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.foodrecipe.domain.model.Recipe
import com.example.foodrecipe.ui.saved.SavedViewModel

@Composable
fun RecipeDetailsScreen(
    recipeId: Int,
    onBackClick: () -> Unit,
    viewModel: RecipeViewModel = hiltViewModel(),
    savedViewModel: SavedViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val isSaved by savedViewModel.isSaved.collectAsState()

    LaunchedEffect(recipeId) {
        viewModel.loadRecipe(recipeId)
        savedViewModel.observeRecipe(recipeId)
    }

    LaunchedEffect(isSaved) {
        Log.d("RecipeDetailsScreen", "isSaved: $isSaved")
    }

    when (uiState) {

        is RecipeUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is RecipeUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text((uiState as RecipeUiState.Error).message)
            }
        }

        is RecipeUiState.Success -> {
            RecipeDetailsContent(
                recipe = (uiState as RecipeUiState.Success).recipe,
                isSaved = isSaved,
                onSaveClick ={
                    savedViewModel.toggleSave(recipeId)
                },
                onBackClick = onBackClick
            )
        }
    }
}
