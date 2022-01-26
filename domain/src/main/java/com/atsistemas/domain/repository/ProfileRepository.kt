package com.atsistemas.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by Carlos Mateo Benito on 26/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
interface ProfileRepository {

    suspend fun saveSurname(surname:String)

    suspend fun getSurname(): Flow<String>

    suspend fun saveName(name:String)

    suspend  fun getName():String
}