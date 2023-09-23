package com.ilhomsoliev.myfinances.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class DataStoreManager(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")
        val token_key = stringPreferencesKey("token_key")
        val username_key = stringPreferencesKey("username_key")
        val refresh_token_key = stringPreferencesKey("refresh_token_key")
        val phone_key = stringPreferencesKey("phone_key")
        val is_first_time_key = booleanPreferencesKey("is_first_time_key")
    }

    suspend fun changeToken(value: String) {
        context.dataStore.edit { preferences ->
            preferences[token_key] = value
        }
    }

    suspend fun changeIsFirstTime(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[is_first_time_key] = value
        }
    }

    suspend fun changeUsername(value: String) {
        context.dataStore.edit { preferences ->
            preferences[username_key] = value
        }
    }

    suspend fun changeRefreshToken(value: String) {
        context.dataStore.edit { preferences ->
            preferences[refresh_token_key] = value
        }
    }

    suspend fun changePhone(value: String) {
        context.dataStore.edit { preferences ->
            preferences[phone_key] = value
        }
    }

    suspend fun getTokens() = withContext(IO) {
        val token = context.dataStore.data.first()[token_key]
        Log.d("Hello", token.toString())
        token
    }

    suspend fun getUsername() = withContext(IO) {
        context.dataStore.data.first()[username_key] ?: ""
    }

    suspend fun getRefreshToken() = withContext(IO) {
        context.dataStore.data.first()[refresh_token_key] ?: ""
    }

    suspend fun getPhone() = withContext(IO) {
        context.dataStore.data.first()[phone_key] ?: ""
    }

    suspend fun getIsFirstTime() = withContext(IO) {
        context.dataStore.data.first()[is_first_time_key] ?: true
    }
}