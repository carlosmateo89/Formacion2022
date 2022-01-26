package com.atsistemas.formacion2022.data.database.room

import androidx.room.*

/**
 * Created by Carlos Mateo Benito on 19/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
@Dao
interface TransactionsDAO {

    @Query("SELECT * from ${TransactionDataDB.NAME}")
    fun getTransactions(): List<TransactionDataDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransactions(vararg transactionModel: TransactionDataDB)

    @Query("DELETE from ${TransactionDataDB.NAME}")
    suspend fun deleteTransactions()

    @Delete
    suspend fun deleteItemTransaction(transaction: TransactionDataDB)


}