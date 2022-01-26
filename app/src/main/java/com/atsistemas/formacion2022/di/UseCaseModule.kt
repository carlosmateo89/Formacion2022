package com.atsistemas.formacion2022.di

import com.atsistemas.domain.usecase.*
import com.atsistemas.formacion2022.data.di.InjectionQualifiers
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

/**
 * Created by Carlos Mateo Benito on 26/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
val usecaseModule = module {

    single { GetProfileSurnameUseCase(get()) }

    single { GetProfileNameUseCase(get()) }

    single { SaveProfileNameUseCase(get()) }

    single { SaveProfileSurnameUseCase(get()) }

    single { GetSavedTransactionsUseCase(get(qualifier = qualifier(InjectionQualifiers.DB))) }

    single {
        UpdateTransactionsUseCase(
            remoteRepository = get(qualifier = qualifier(InjectionQualifiers.REMOTE)),
            dbRepository = get()
        )
    }

    single { DeleteTransactionUseCase(get()) }

    single { SaveTransactionsUseCase(get()) }
}



