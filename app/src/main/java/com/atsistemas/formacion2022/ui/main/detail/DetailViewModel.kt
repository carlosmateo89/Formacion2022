package com.atsistemas.formacion2022.ui.main.detail

import androidx.lifecycle.MutableLiveData
import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.formacion2022.common.BaseViewModel

/**
 * Created by Carlos Mateo Benito on 24/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class DetailViewModel : BaseViewModel() {

    private val liveTransaction = MutableLiveData<TransactionModel>()
    val obsTransaction = liveTransaction

    fun onAttachTransaction(transaction: TransactionModel) {
        liveTransaction.value = transaction
        mainViewModel.showFab(false)
    }

    override fun onInitialization() {

    }

    override fun onCleared() {
        mainViewModel.showFab(true)
        super.onCleared()
    }
}