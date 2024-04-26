package com.suslanium.wordsfactory.data.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.suslanium.wordsfactory.data.Constants
import kotlinx.coroutines.flow.first

private val Context.localTimeStampSource by preferencesDataStore(Constants.PREFS_NAME)

class TestTimestampDataSource(private val context: Context) {

    private val timestampId = longPreferencesKey(Constants.TIMESTAMP_KEY)

    suspend fun getTimestamp(): Long {
        return context.localTimeStampSource.data.first()[timestampId] ?: 0
    }

    suspend fun setTimestamp(timestamp: Long) {
        context.localTimeStampSource.edit { preferences ->
            preferences[timestampId] = timestamp
        }
    }

}