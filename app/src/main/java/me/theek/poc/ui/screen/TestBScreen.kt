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
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import me.theek.poc.ui.viewmodel.TestViewModel

@Composable
fun TestBScreen(
    testViewModel: TestViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Log.d("TestBScreenRecomposition", "Unnecessary Recomposition")
        Text(text = "Test B Screen")

        ComponentB(
            isLoading = testViewModel.uiState.isLoading,
            username = testViewModel.uiState.username,
            password = testViewModel.uiState.password,
            onClickMe = testViewModel::updateState
        )
        
        Text(text = "You're awesome! Thank you for taking the time to review my code ❤️", textAlign = TextAlign.Center)
    }
}

@Composable
fun ComponentB(
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
            Text(text = "Save State & check log")
        }
    }
}