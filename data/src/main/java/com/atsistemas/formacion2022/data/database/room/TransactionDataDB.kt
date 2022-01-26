package com.atsistemas.formacion2022.data.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atsistemas.domain.model.TransactionModel
import java.io.Serializable

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */

@Entity(tableName = TransactionDataDB.NAME)
data class TransactionDataDB(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id:String,
    @ColumnInfo(name = DATE)
    val date:String,
    @ColumnInfo(name = AMOUNT)
    val amount:String,
    @ColumnInfo(name = DESCRIPTION)
    val description:String?,
    @ColumnInfo(name = FEE)
    val fee:String?,
    @ColumnInfo(name = TOTAL)
    val total:String?
): Serializable{

    companion object TABLE{
        const val NAME = "transactions"
        const val ID = "id"
        const val DATE = "date"
        const val AMOUNT = "amount"
        const val DESCRIPTION = "description"
        const val FEE = "fee"
        const val TOTAL = "total"
    }
}

fun TransactionModel.toDataDB() : TransactionDataDB {
    return TransactionDataDB(
        id, date, amount, description, fee, total
    )
}

fun TransactionDataDB.toDomain() : TransactionModel{
    return TransactionModel(
        id, date, amount, description, fee, total
    )
}