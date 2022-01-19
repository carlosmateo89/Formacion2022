package com.atsistemas.formacion2022.data.repository

import com.atsistemas.formacion2022.data.database.AppDatabase
import com.atsistemas.formacion2022.data.model.TransactionModel
import com.atsistemas.formacion2022.data.remote.TransactionAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

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
    private val transactionAPI: TransactionAPI,
    private val db: AppDatabase
) {

    suspend fun getTransactionsRemotely():List<TransactionModel>{
        return transactionAPI.getTransactions()
    }

    suspend fun saveTransactions(vararg transactionModel: TransactionModel){
        db.transactionsDao().saveTransactions(*transactionModel)
    }

    suspend fun getTransactionsLocally(): List<TransactionModel>{
        return db.transactionsDao().getTransactions()
    }

    suspend fun deleteTransactions(){
        db.transactionsDao().deleteTransactions()
    }

    suspend fun getTransactionsAndSave():List<TransactionModel>{
        return withContext(Dispatchers.IO) {
            val data = getTransactionsRemotely()
            saveTransactions(*data.toTypedArray())
            data
        }
    }
}