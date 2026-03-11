package com.example.foodrecipe.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.foodrecipe.R
import com.example.foodrecipe.ui.recipe.AddRecipeScreen
import com.example.foodrecipe.ui.auth.ProfileScreen
import com.example.foodrecipe.ui.home.HomeScreen
import com.example.foodrecipe.ui.notification.NotificationScreen
import com.example.foodrecipe.ui.recipe.RecipeDetailsScreen
import com.example.foodrecipe.ui.saved.SavedScreen

fun androidx.navigation.NavGraphBuilder.mainNavGraph(
    rootNavController: NavController
) {

    navigation(
        startDestination = Screen.Home.route,
        route = Screen.MainGraph.route
    ) {
        composable(Screen.Home.route) {
            MainScreen(rootNavController)
        }
    }
}

@Composable
fun MainScreen(
    rootNavController: NavController
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background
            ) {

                val selectedColor = Color(0xFF4CAF50)   // Green
                val unselectedColor = Color.Gray

                // HOME
                NavigationBarItem(
                    selected = currentRoute == Screen.Home.route,
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent, // removes background highlight
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor
                    ),
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.fr_home_icon),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)   // 🔥 Reduced size
                        )
                    }
                )

                // SAVE
                NavigationBarItem(
                    selected = currentRoute == Screen.Save.route,
                    onClick = {
                        navController.navigate(Screen.Save.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor
                    ),
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.fr_save_icon),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                )

                // ADD
                NavigationBarItem(
                    selected = currentRoute == Screen.Add.route,
                    onClick = {
                        navController.navigate(Screen.Add.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor
                    ),
                    icon = {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                )

                // NOTIFICATION
                NavigationBarItem(
                    selected = currentRoute == Screen.Notification.route,
                    onClick = {
                        navController.navigate(Screen.Notification.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor
                    ),
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.fr_bell_icon),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                )

                // PROFILE
                NavigationBarItem(
                    selected = currentRoute == Screen.Profile.route,
                    onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor
                    ),
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.fr_profile_icon),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                )
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {

            composable(Screen.Home.route) {
                HomeScreen(
                    onRecipeClick = { recipeId ->
                            navController.navigate(
                                Screen.RecipeDetails.createRoute(recipeId)
                            )
                    }
                )
            }

            composable(Screen.Save.route) {
                SavedScreen(navController=navController)
            }

            composable(Screen.Add.route) {
                AddRecipeScreen()
            }

            composable(Screen.Notification.route) {
                NotificationScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    onLogout = {
                        rootNavController.navigate(Screen.AuthGraph.route) {
                            popUpTo(Screen.MainGraph.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.RecipeDetails.route,
                arguments = listOf(
                    navArgument("id"){type = NavType.IntType}
                )
            ){
                backStackEntry ->

                val recipeId = backStackEntry.arguments?.getInt("id") ?: 0
                RecipeDetailsScreen(recipeId = recipeId,
                    onBackClick = {navController.popBackStack()})
            }


        }
    }
}
