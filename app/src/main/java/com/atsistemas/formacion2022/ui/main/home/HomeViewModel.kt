package com.atsistemas.formacion2022.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.atsistemas.formacion2022.common.BaseViewModel
import com.atsistemas.formacion2022.common.NavData
import com.atsistemas.formacion2022.data.model.TransactionModel
import com.atsistemas.formacion2022.data.remote.ResultHandler
import com.atsistemas.formacion2022.data.repository.TransactionRepository
import com.atsistemas.formacion2022.ui.dialog.DialogData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    companion object{
        const val NAV_DETAIL = 0
    }

    private val liveListTransactions by lazy {
        transactionRepository.getTransactionsLocally()
    }

    val obsListTransactions: LiveData<List<TransactionModel>> = liveListTransactions


    fun onInit() {

    }

    fun onActionDownloadClicked() {
        showLoading()
        viewModelScope.launch {
            val result = transactionRepository.updateTransactions()
            delay(2000)
            when(result){
                is ResultHandler.GenericError -> {
                    liveShowDialog.value = DialogData(true,result.message)
                }
                is ResultHandler.HttpError -> {
                    liveShowDialog.value = DialogData(true,"Http: ${result.code} ${result.message}")
                }
                is ResultHandler.NetworkError -> {
                    liveShowDialog.value = DialogData(true,"Network error")
                }
                is ResultHandler.Success -> {
                    liveShowMessage.value = "Data got from remote successfully"
                }
            }
            hideLoading()
        }

    }

    fun onActionTransactionClicked(transactionModel: TransactionModel) {
        navigate(NavData(NAV_DETAIL,transactionModel))
    }

    fun onActionOnItemSwiped(itemPosition: Int) {
        viewModelScope.launch {
            val transaction = obsListTransactions.value?.get(itemPosition)
            transaction?.also {
                transactionRepository.deleteTransaction(it)
            }
        }
    }
}