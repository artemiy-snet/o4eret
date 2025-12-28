package com.example.o4eret.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID

private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

class AppStore(context: Context) {

    private val dataStore = context.appDataStore

    val roleFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[ROLE_KEY]
    }

    val nodeIdFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[NODE_ID_KEY]
    }

    val crtEnabledFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[CRT_ENABLED_KEY] ?: false
    }

    suspend fun ensureNodeId(): String = withContext(Dispatchers.IO) {
        var currentId: String? = null
        dataStore.edit { preferences ->
            currentId = preferences[NODE_ID_KEY]
            if (currentId == null) {
                val generated = UUID.randomUUID().toString()
                preferences[NODE_ID_KEY] = generated
                currentId = generated
            }
        }
        currentId ?: UUID.randomUUID().toString()
    }

    suspend fun setRole(role: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[ROLE_KEY] = role
            }
        }
    }

    suspend fun setCrt(enabled: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[CRT_ENABLED_KEY] = enabled
            }
        }
    }

    companion object {
        private val ROLE_KEY = stringPreferencesKey("role")
        private val NODE_ID_KEY = stringPreferencesKey("node_id")
        private val CRT_ENABLED_KEY = booleanPreferencesKey("crt_enabled")
    }
}
