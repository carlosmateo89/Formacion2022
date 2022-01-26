package com.atsistemas.domain.usecase

import com.atsistemas.domain.model.TransactionModel
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
class GetSavedTransactionsUseCase(
    private val transactionRepository: GetTransactionRepository
): UseCase<Unit, List<TransactionModel>>() {

    override suspend fun executeUseCase(input: Unit):List<TransactionModel> {
        return transactionRepository.getTransactions().getOrDefault(emptyList())
    }
}