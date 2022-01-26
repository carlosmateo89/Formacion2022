package com.atsistemas.formacion2022.data.remote

import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.domain.repository.EditTransactionRepository
import com.atsistemas.domain.repository.GetTransactionRepository
import java.util.*

/**
 * Created by Carlos Mateo Benito on 26/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class MockLocalRepository : EditTransactionRepository,GetTransactionRepository {

    private val map : SortedMap<Int,TransactionModel> = sortedMapOf()

    override suspend fun deleteTransaction(transactionModel: TransactionModel) {
       map.remove(transactionModel.id.toInt())
    }

    override suspend fun saveTransactions(vararg transactions: TransactionModel) {
      transactions.forEach {
          map[it.id.toInt()] = it
      }
    }

    override suspend fun getTransactions(): Result<List<TransactionModel>> {
       return Result.success(map.values.toList())
    }

}