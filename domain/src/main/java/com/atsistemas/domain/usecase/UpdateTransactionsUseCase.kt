package com.atsistemas.domain.usecase

import com.atsistemas.domain.common.formatDate
import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.domain.repository.EditTransactionRepository
import com.atsistemas.domain.repository.GetTransactionRepository

/**
 * Created by Carlos Mateo Benito on 25/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class UpdateTransactionsUseCase(
    private val remoteRepository: GetTransactionRepository,
    private val dbRepository: EditTransactionRepository
) : UseCase<Unit, Result<List<TransactionModel>>>() {

    override suspend fun executeUseCase(input: Unit): Result<List<TransactionModel>> {
        return  remoteRepository.getTransactions().map {
            val listFormatted = it.map { it.copy(date = it.date.formatDate() ?: "")  }
            dbRepository.saveTransactions(*listFormatted.toTypedArray())
            listFormatted
        }
    }


}