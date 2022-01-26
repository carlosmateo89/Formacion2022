package com.atsistemas.formacion2022.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.atsistemas.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Created by Carlos Mateo Benito on 25/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */

class DataStoreProfileRepository(private val context:Context) : ProfileRepository {

    private val Context.storeData: DataStore<Preferences> by preferencesDataStore(name = "ProfileSettings")
    private val KEY_USER_NAME = stringPreferencesKey("KEY_USER_NAME")
    private val KEY_USER_SURNAME = stringPreferencesKey("KEY_USER_SURNAME")


    override suspend fun saveSurname(surname: String) {
        withContext(Dispatchers.IO) {
            context.storeData.edit { preferences->
                preferences[KEY_USER_SURNAME] = surname

            }
        }
    }

    override suspend fun getSurname(): Flow<String> {
        return context.storeData.data.map { preferences->
            preferences[KEY_USER_SURNAME]?:""
        }
    }

    override suspend fun saveName(name: String) {
        withContext(Dispatchers.IO) {
            context.storeData.edit { preferences->
                preferences[KEY_USER_NAME] = name

            }
        }
    }

    override suspend fun getName(): String {
        return context.storeData.data.map { preferences->
            preferences[KEY_USER_NAME]?:""
        }.first()
    }
}