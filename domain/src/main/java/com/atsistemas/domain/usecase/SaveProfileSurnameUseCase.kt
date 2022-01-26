package com.atsistemas.domain.usecase

import com.atsistemas.domain.repository.ProfileRepository

/**
 * Created by Carlos Mateo Benito on 25/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class SaveProfileSurnameUseCase(
    private val profileRepository: ProfileRepository
): UseCase<String, Unit>() {

    override suspend fun executeUseCase(input: String) {
        return profileRepository.saveSurname(input)
    }
}