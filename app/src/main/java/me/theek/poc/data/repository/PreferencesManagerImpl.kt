package me.theek.poc.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import me.theek.poc.domain.repository.PreferencesManager
import me.theek.poc.util.Constants.ACCESS_TOKEN_KEY
import me.theek.poc.util.Constants.NO_ACCESS_TOKEN
import me.theek.poc.util.Constants.ON_BOARDING_STATE_KEY
import java.io.IOException

class PreferencesManagerImpl (private val dataStore: DataStore<Preferences>) : PreferencesManager {

    private object PreferenceKeys {
        val ON_BOARDING_STATE = booleanPreferencesKey(name = ON_BOARDING_STATE_KEY)
        val ACCESS_TOKEN = stringPreferencesKey(name = ACCESS_TOKEN_KEY)
    }

    override suspend fun saveOnBoardingState(isAccept: Boolean) {
        dataStore.edit {
            it[PreferenceKeys.ON_BOARDING_STATE] = isAccept
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emptyPreferences()
                }
            }
            .map {
                it[PreferenceKeys.ON_BOARDING_STATE] ?: false
            }
    }

    override suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit {
            it[PreferenceKeys.ACCESS_TOKEN] = accessToken
        }
    }

    override fun readAccessToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emptyPreferences()
                }
            }.map {
                it[PreferenceKeys.ACCESS_TOKEN] ?: NO_ACCESS_TOKEN
            }
    }
}