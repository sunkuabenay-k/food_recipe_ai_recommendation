package com.example.foodrecipe.ui.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Speed
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodrecipe.R
import com.example.foodrecipe.domain.model.Recipe
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Star
import com.example.foodrecipe.ui.saved.SavedViewModel

import android.util.Log

private val Green = Color(0xFF0C560D)

enum class RecipeTab {
    INGREDIENTS,
    INSTRUCTIONS
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RecipeDetailsContent(
    recipe: Recipe,
    isSaved: Boolean,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit
) {

    var selectedTab by remember { mutableStateOf(RecipeTab.INGREDIENTS) }

    LaunchedEffect(isSaved) {
        Log.d("RecipeDetailsContent", "isSaved: $isSaved")
    }
    Scaffold(
        containerColor = Color(0xFFF7F7F7),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "More options"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }


    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            item {

                // ================= IMAGE =================
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(RoundedCornerShape(24.dp))
                ) {

                    AsyncImage(
                        model = recipe.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.7f)
                                    )
                                )
                            )
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // TIME (Left)
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Icon(
                                painter = painterResource(R.drawable.ic_clock),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = "${recipe.prepTimeMinutes + recipe.cookTimeMinutes} min",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // SAVE ICON (Right)
                        IconButton(
                            onClick = onSaveClick,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSaved) Color.White else Color.Transparent
                                )
                        ) {

                            Icon(
                                painter = painterResource(R.drawable.ic_save),
                                contentDescription = null,
                                tint = if (isSaved) Color(0xFFE91E63) else Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                }

                // ================= TITLE =================
//                Text(
//                    text = recipe.name,
//                    style = MaterialTheme.typography.headlineSmall,
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))

// ================= PROFESSIONAL STATS CARD =================
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        // Rating Row
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFC107)
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = "${recipe.rating}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = "(${recipe.reviewCount} reviews)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Divider()

                        Spacer(modifier = Modifier.height(16.dp))

                        // Stats Grid
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            StatItem(
                                icon = Icons.Default.Restaurant,
                                label = "Servings",
                                value = "${recipe.servings}"
                            )

                            StatItem(
                                icon = Icons.Default.LocalFireDepartment,
                                label = "Calories",
                                value = "${recipe.caloriesPerServing} kcal"
                            )

                            StatItem(
                                icon = Icons.Default.Speed,
                                label = "Difficulty",
                                value = recipe.difficulty
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                // ================= TAGS =================
                FlowRow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    (recipe.tags + recipe.mealType).forEach { tag ->
                        AssistChip(
                            onClick = {},
                            label = { Text(tag) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ================= SEGMENTED CONTROL =================
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFEDEDED))
                        .padding(4.dp)
                ) {

                    SegmentedTab(
                        text = "Ingredients",
                        selected = selectedTab == RecipeTab.INGREDIENTS,
                        modifier = Modifier.weight(1f)
                    ) { selectedTab = RecipeTab.INGREDIENTS }

                    SegmentedTab(
                        text = "Instructions",
                        selected = selectedTab == RecipeTab.INSTRUCTIONS,
                        modifier = Modifier.weight(1f)
                    ) { selectedTab = RecipeTab.INSTRUCTIONS }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // ================= CONTENT =================
            if (selectedTab == RecipeTab.INGREDIENTS) {

                itemsIndexed(recipe.ingredients) { _, ingredient ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = ingredient,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

            } else {

                itemsIndexed(recipe.instructions) { index, instruction ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF3F3F3)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                "${index + 1}. ",
                                fontWeight = FontWeight.Bold
                            )
                            Text(instruction)
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

@Composable
fun InfoItem(icon: String, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(icon)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun SegmentedTab(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(25))
            .background(if (selected) Green else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color.Black
        )
    }
}

@Composable
fun StatItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Green,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}
