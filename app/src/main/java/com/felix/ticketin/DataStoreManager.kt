package com.felix.ticketin

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: android.content.Context) {

    suspend fun setPass(passValue : String){
        context.passDataStore.edit{preferences ->
            preferences [PASS_KEY] = passValue
        }
    }

    fun getPass(): Flow<String>{
        return context.passDataStore.data.map { preferences ->
            preferences [PASS_KEY]?:""
        }
    }

    companion object{
        private const val DATASTORE_NAME = "pass_preferences"

        private val PASS_KEY = stringPreferencesKey("pass_key")

        private val android.content.Context.passDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}