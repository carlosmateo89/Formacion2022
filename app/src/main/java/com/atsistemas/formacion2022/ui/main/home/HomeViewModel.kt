package com.atsistemas.formacion2022.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atsistemas.domain.exception.TransactionExceptions
import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.domain.usecase.DeleteTransactionUseCase
import com.atsistemas.domain.usecase.GetSavedTransactionsUseCase
import com.atsistemas.domain.usecase.UpdateTransactionsUseCase
import com.atsistemas.formacion2022.common.BaseViewModel
import com.atsistemas.formacion2022.common.NavData
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
    private val updateTransactionsUseCase: UpdateTransactionsUseCase,
    private val getSavedTransactionsUseCase: GetSavedTransactionsUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase
) : BaseViewModel() {

    companion object {
        const val NAV_DETAIL = 0
    }

    private val liveListTransactions: MutableLiveData<List<TransactionModel>> = MutableLiveData()
    val obsListTransactions: LiveData<List<TransactionModel>> = liveListTransactions


    override fun onInitialization() {
        executeUseCase {
            liveListTransactions.value = getSavedTransactionsUseCase.execute(Unit)
        }
    }

    fun onActionDownloadClicked() {
        showLoading()
        executeUseCase(
            finalAction = {
                hideLoading()
            }
        ) {
            val result = updateTransactionsUseCase.execute(Unit)
            result.onSuccess {
                liveListTransactions.value = it
                liveShowMessage.value = "Data got from remote successfully"
            }.onFailure {
                handleUpdateException(it)
            }
        }
    }

    private fun handleUpdateException(it: Throwable) {
        if (it is TransactionExceptions) {
            when (it) {
                is TransactionExceptions.GenericError -> {
                    liveShowDialog.value = DialogData(true, it.message)
                }
                is TransactionExceptions.HttpError -> {
                    liveShowDialog.value = DialogData(true, "Http: ${it.code} ${it.message}")

                }
                is TransactionExceptions.NetworkError -> {
                    liveShowDialog.value = DialogData(true, "Network error")
                }
            }
        }
    }

    fun onActionTransactionClicked(transactionModel: TransactionModel) {
        navigate(NavData(NAV_DETAIL, transactionModel))
    }

    fun onActionOnItemSwiped(itemPosition: Int) {
        executeUseCase {
            val transaction = obsListTransactions.value?.get(itemPosition)
            transaction?.also {
                deleteTransactionUseCase.execute(it)
                liveListTransactions.value = getSavedTransactionsUseCase.execute(Unit)
            }


        }
    }
}