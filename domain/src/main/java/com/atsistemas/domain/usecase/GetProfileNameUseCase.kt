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
class GetProfileNameUseCase(
    private val profileRepository: ProfileRepository
): UseCase<Unit, String>() {

    override suspend fun executeUseCase(input: Unit):String {
        return profileRepository.getName()
    }
}