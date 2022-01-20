package com.atsistemas.formacion2022.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atsistemas.formacion2022.common.BaseViewModel
import com.atsistemas.formacion2022.data.model.TransactionModel
import com.atsistemas.formacion2022.data.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class HomeViewModel(
    private val transactionRepository: TransactionRepository
) : BaseViewModel() {


    private val liveListTransactions by lazy {
        transactionRepository.getTransactionsLocally()
    }

    val obsListTransactions: LiveData<List<TransactionModel>> = liveListTransactions

    fun onInit() {

    }

    fun onActionDownloadClicked() {
        viewModelScope.launch {
            val result = transactionRepository.updateTransactions()
            when(result){
                is ResultHandler.GenericError -> {
                    liveShowDialog.value = DialogData(true)
                }
                is ResultHandler.HttpError -> {
                    liveShowDialog.value = DialogData(true)
                }
                is ResultHandler.NetworkError -> {
                    liveShowDialog.value = DialogData(true)
                }
                is ResultHandler.Success -> {

                }
            }
        }

    }

    fun onActionTransactionClicked(transactionModel: TransactionModel) {
        viewModelScope.launch {
            transactionRepository.deleteTransactions()
       }
    }

}