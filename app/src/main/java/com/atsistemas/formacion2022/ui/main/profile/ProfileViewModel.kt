package com.atsistemas.formacion2022.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.atsistemas.domain.usecase.GetProfileNameUseCase
import com.atsistemas.domain.usecase.GetProfileSurnameUseCase
import com.atsistemas.domain.usecase.SaveProfileNameUseCase
import com.atsistemas.domain.usecase.SaveProfileSurnameUseCase
import com.atsistemas.formacion2022.common.BaseViewModel
import com.atsistemas.formacion2022.data.repository.DataStoreProfileRepository
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
class ProfileViewModel(
    private val getProfileNameUseCase: GetProfileNameUseCase,
    private val getProfileSurnameUseCase: GetProfileSurnameUseCase,
    private val saveProfileSurnameUseCase: SaveProfileSurnameUseCase,
    private val saveProfileNameUseCase: SaveProfileNameUseCase
) : BaseViewModel() {

    override fun onInitialization() {
        mainViewModel.showFab(false)
        executeUseCase {
            val name = getProfileNameUseCase.execute(Unit)
            liveName.value = name
        }
    }

    val obsSurname: LiveData<String> =
        getProfileSurnameUseCase.executeSyncInCurrentThread(Unit).asLiveData()

    private val liveName = MutableLiveData<String>()
    val obsName: LiveData<String> = liveName

    fun onActionNameWritten(name: String) {
        liveName.value = name
        executeUseCase {
            saveProfileNameUseCase.execute(name)
        }
    }

    fun onActionSurnameWritten(surname: String) {
        saveProfileSurnameUseCase.executeSyncInCurrentThread(surname)
    }

    override fun onCleared() {
        mainViewModel.showFab(true)
        super.onCleared()
    }
}