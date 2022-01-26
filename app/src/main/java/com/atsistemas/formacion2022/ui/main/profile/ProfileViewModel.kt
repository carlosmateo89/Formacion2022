package com.atsistemas.formacion2022.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.atsistemas.formacion2022.common.BaseViewModel
import com.atsistemas.formacion2022.data.repository.ProfileRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by Carlos Mateo Benito on 25/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class ProfileViewModel(private val profileRepository: ProfileRepository) : BaseViewModel() {

    val obsSurname: LiveData<String> = profileRepository.getUserSurname().asLiveData()
    val obsName: LiveData<String> = profileRepository.getUserName().asLiveData()

    fun onActionNameWritten(name: String) {
        runBlocking {
            profileRepository.saveUserName(name)
        }
    }

    fun onActionSurnameWritten(surname: String) {
        runBlocking {
            profileRepository.saveUserSurname(surname)
        }
    }

}