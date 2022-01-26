package com.atsistemas.domain.usecase

import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.domain.repository.EditTransactionRepository

/**
 * Created by Carlos Mateo Benito on 25/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class SaveTransactionsUseCase(
    private val transactionRepository: EditTransactionRepository
): UseCase<List<TransactionModel>, Unit>() {

    override suspend fun executeUseCase(input: List<TransactionModel>) {
        transactionRepository.saveTransactions(*input.toTypedArray())
    }


}