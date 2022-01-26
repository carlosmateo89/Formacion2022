package com.atsistemas.formacion2022.data.database.room

import android.content.Context
import androidx.room.Room
import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.domain.repository.EditTransactionRepository
import com.atsistemas.domain.repository.GetTransactionRepository

/**
 * Created by Carlos Mateo Benito on 26/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class RoomTransactionRepository(private val context: Context): EditTransactionRepository,GetTransactionRepository {

    private val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        com.atsistemas.formacion2022.data.BuildConfig.DB_NAME
    ).build()

    override suspend fun deleteTransaction(transactionModel: TransactionModel) {
        appDatabase.transactionsDao().deleteItemTransaction(transactionModel.toDataDB())
    }

    override suspend fun saveTransactions(vararg transactions: TransactionModel) {
        appDatabase.transactionsDao().saveTransactions(*transactions.map { it.toDataDB() }.toTypedArray())
    }

    override suspend fun getTransactions(): Result<List<TransactionModel>> {
        val list = appDatabase.transactionsDao().getTransactions().map {
            it.toDomain()
        }

        return Result.success(list)
    }
}