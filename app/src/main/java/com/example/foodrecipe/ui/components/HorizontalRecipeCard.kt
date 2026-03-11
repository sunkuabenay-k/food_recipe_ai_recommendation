package com.example.foodrecipe.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodrecipe.domain.model.Recipe

@Composable
fun HorizontalRecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .width(200.dp)
            .height(260.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        // Main Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
//                .height(180.dp)
                .align(Alignment.BottomCenter)
                .padding(top = 70.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF4F4F4),
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = recipe.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    minLines = 2, // Forces 1-line names to take up 2 lines of space
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Time",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Text(
                    text = "${recipe.prepTimeMinutes} Mins",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Circular Image (Half Outside)
        AsyncImage(
            model = recipe.image,
            contentDescription = recipe.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
//                .offset(y = (-40).dp)
        )

        // Rating Pill
        Surface(
            shape = RoundedCornerShape(50),
            color = Color(0xFFFFC107),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-20).dp, y = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = recipe.rating.toString(),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HorizontalRecipeCardPreview() {
//
//    val sampleRecipe = Recipe(
//        id = 1,
//        name = "Chicken Biryani",
//        image = "https://cdn.dummyjson.com/recipe-images/1.webp",
//        prepTimeMinutes = 20,
//        cookTimeMinutes = 15,
//        servings = 2,
//        difficulty = "Medium",
//        cuisine = "Italian",
//        caloriesPerServing = 450,
//        rating = 4.5,
//        reviewCount = 120,
//        ingredients = listOf(
//            "Spaghetti",
//            "Eggs",
//            "Parmesan",
//            "Bacon"
//        ),
//        instructions = listOf(
//            "Boil pasta",
//            "Cook bacon",
//            "Mix eggs and cheese",
//            "Combine everything"
//        ),
//        tags = listOf("Pasta", "Dinner"),
//        mealType = listOf("Lunch", "Dinner")
//    )
//
//    MaterialTheme {
//        HorizontalRecipeCard(recipe = sampleRecipe)
//    }
//}
//
