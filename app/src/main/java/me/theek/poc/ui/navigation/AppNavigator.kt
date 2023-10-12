package me.theek.poc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.theek.poc.ui.screen.TestAScreen
import me.theek.poc.ui.screen.TestBScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.TestA.path
    ) {
        composable(route = Screen.TestA.path) {
            TestAScreen(
                onNavigateToB = { navController.navigate(Screen.TestB.path) }
            )
        }

        composable(route = Screen.TestB.path) {
            TestBScreen()
        }
    }
}