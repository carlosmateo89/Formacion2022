package com.atsistemas.formacion2022.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.atsistemas.formacion2022.data.model.TransactionModel

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

    @Query("SELECT * from ${TransactionModel.NAME}")
    fun getTransactions(): LiveData<List<TransactionModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransactions(vararg transactionModel: TransactionModel)

    @Query("DELETE from ${TransactionModel.NAME}")
    suspend fun deleteTransactions()

    @Delete
    suspend fun deleteItemTransaction(transaction:TransactionModel)


}