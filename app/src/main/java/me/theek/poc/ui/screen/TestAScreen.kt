package me.theek.poc.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import me.theek.poc.ui.viewmodel.TestViewModel

@Composable
fun TestAScreen(
    testViewModel: TestViewModel = hiltViewModel(),
    onNavigateToB: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Log.d("TestAScreenRecomposition", "Unnecessary Recomposition")
        Text(text = "Test A Screen")

        ComponentA(
            isLoading = testViewModel.uiState.isLoading,
            username = testViewModel.uiState.username,
            password = testViewModel.uiState.password,
            onClickMe = {
                testViewModel.updateState()
            }
        )

        Button(onClick = onNavigateToB) {
            Text(text = "Navigate to B")
        }
    }
}

@Composable
fun ComponentA(
    isLoading: Boolean,
    username: String,
    password: String,
    onClickMe: () -> Unit
) {
    Column {
        if (isLoading) CircularProgressIndicator()

        Text(text = username)
        Text(text = password)

        Button(onClick = onClickMe) {
            Text(text = "First Save State & check log :)")
        }
    }
}