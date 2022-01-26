package com.atsistemas.domain.usecase

import com.atsistemas.domain.repository.ProfileRepository
import com.atsistemas.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Carlos Mateo Benito on 25/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class GetProfileSurnameUseCase(
    private val profileRepository: ProfileRepository
): UseCase<Unit, Flow<String>>() {

    override suspend fun executeUseCase(input: Unit):Flow<String> {
        return profileRepository.getSurname()
    }
}