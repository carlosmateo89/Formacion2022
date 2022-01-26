package com.atsistemas.formacion2022.data.di

import com.atsistemas.domain.repository.EditTransactionRepository
import com.atsistemas.domain.repository.GetTransactionRepository
import com.atsistemas.domain.repository.ProfileRepository
import com.atsistemas.formacion2022.data.remote.MockLocalRepository
import com.atsistemas.formacion2022.data.remote.MockRemoteRepository
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

    single { MockLocalRepository() }

    single<GetTransactionRepository>(qualifier = qualifier(InjectionQualifiers.DB)) {
        get<MockLocalRepository>()
    }

    single<GetTransactionRepository>(qualifier = qualifier(InjectionQualifiers.REMOTE)) {
        MockRemoteRepository()
    }

    single<EditTransactionRepository> {
        get<MockLocalRepository>()
    }

    single<ProfileRepository> {
        DataStoreProfileRepository(get())
    }
}