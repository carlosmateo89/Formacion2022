package com.atsistemas.domain.repository

import com.atsistemas.domain.model.TransactionModel

/**
 * Created by Carlos Mateo Benito on 26/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
interface EditTransactionRepository {

    suspend fun deleteTransaction(transactionModel: TransactionModel)

    suspend fun saveTransactions(vararg transactions:TransactionModel)

}