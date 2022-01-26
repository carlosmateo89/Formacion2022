package com.atsistemas.formacion2022.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase

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
        TransactionDataDB::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionsDao(): TransactionsDAO
}