package com.atsistemas.formacion2022.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */

@Entity(tableName = TransactionModel.NAME)
data class TransactionModel(
    @PrimaryKey
    val id:String,
    @ColumnInfo(name = TABLE.DATE)
    val date:String,
    val amount:String,
    val description:String?,
    val fee:String?,
    val total:String?
){

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