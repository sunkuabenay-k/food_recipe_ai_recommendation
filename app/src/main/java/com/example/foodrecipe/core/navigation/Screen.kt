package com.example.foodrecipe.core.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object AuthGraph : Screen("auth_graph")
    object MainGraph : Screen("main_graph")

    object SignIn : Screen("sign_in")
    object SignUp : Screen("sign_up")

    object Home : Screen("home")
    object Save : Screen("save")
    object Notification : Screen("notification")
    object Profile : Screen("profile")
    object Add : Screen("add")
}
