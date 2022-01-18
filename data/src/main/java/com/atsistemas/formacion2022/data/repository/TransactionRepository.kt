package com.atsistemas.formacion2022.data.repository

import com.atsistemas.formacion2022.data.model.TransactionModel
import com.atsistemas.formacion2022.data.remote.TransactionAPI

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class TransactionRepository(
    private val transactionAPI: TransactionAPI
) {

    suspend fun getTransactions():List<TransactionModel>{
        return transactionAPI.getTransactions()
    }
}