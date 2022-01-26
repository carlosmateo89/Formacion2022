package com.atsistemas.formacion2022.data.di

import com.atsistemas.domain.repository.EditTransactionRepository
import com.atsistemas.domain.repository.GetTransactionRepository
import com.atsistemas.domain.repository.ProfileRepository
import com.atsistemas.formacion2022.data.database.room.RoomTransactionRepository
import com.atsistemas.formacion2022.data.remote.retrofit.RetrofitTransactionRepository
import com.atsistemas.formacion2022.data.repository.DataStoreProfileRepository
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
val dataModule = module {

    single { RoomTransactionRepository(get()) }

    single<GetTransactionRepository>(qualifier = qualifier(InjectionQualifiers.DB)) {
       get<RoomTransactionRepository>()
    }

    single<GetTransactionRepository>(qualifier = qualifier(InjectionQualifiers.REMOTE)) {
        RetrofitTransactionRepository()
    }

    single<EditTransactionRepository> {
        get<RoomTransactionRepository>()
    }

    single<ProfileRepository> {
        DataStoreProfileRepository(get())
    }
}