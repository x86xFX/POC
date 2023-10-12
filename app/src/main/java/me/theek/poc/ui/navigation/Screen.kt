package me.theek.poc.ui.navigation

sealed class Screen(val path: String) {
    data object TestA : Screen(path = "test_a_screen")
    data object TestB : Screen(path = "test_b_screen")
}
