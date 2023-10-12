package me.theek.poc.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {
    suspend fun saveOnBoardingState(isAccept: Boolean)
    fun readOnBoardingState() : Flow<Boolean>
    suspend fun saveAccessToken(accessToken: String)
    fun readAccessToken() : Flow<String>
}