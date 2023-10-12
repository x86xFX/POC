package me.theek.poc.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.theek.poc.domain.repository.PreferencesManager
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val preferencesManager: PreferencesManager) : ViewModel(){

    var uiState by mutableStateOf(UiState())
        private set

    fun updateState() {
        uiState = uiState.copy(isLoading = true)

        viewModelScope.launch {
            uiState = try {
                preferencesManager.saveOnBoardingState(isAccept = true)
                delay(5000)
                uiState.copy(isLoading = false)

            } catch (e: Exception) {
                uiState.copy(isLoading = false)
            }
        }
    }
}


data class UiState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = ""
)