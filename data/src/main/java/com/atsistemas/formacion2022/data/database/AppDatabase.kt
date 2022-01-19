package com.atsistemas.formacion2022.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Transaction
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
@Database(
    entities = [
        TransactionModel::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionsDao(): TransactionsDAO
}