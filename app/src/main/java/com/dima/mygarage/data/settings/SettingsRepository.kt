package com.dima.mygarage.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

data class AppSettings(
    val isDarkTheme: Boolean = false
)

class SettingsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    }

    val settings: Flow<AppSettings> = dataStore.data.map { preferences ->
        AppSettings(
            isDarkTheme = preferences[DARK_THEME_KEY] ?: false
        )
    }

    suspend fun setDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = isDarkTheme
        }
    }
}