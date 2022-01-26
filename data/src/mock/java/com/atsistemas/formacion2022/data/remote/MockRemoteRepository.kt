package com.atsistemas.formacion2022.data.remote

import com.atsistemas.domain.exception.TransactionExceptions
import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.domain.repository.GetTransactionRepository
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
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
class MockRemoteRepository  : GetTransactionRepository{

    override suspend fun getTransactions(): Result<List<TransactionModel>> {
        delay(2000L)
        val listTransactions = mutableListOf<TransactionModel>()
        repeat(15) {
            val factor = if (it % 2 == 0)
                1
            else
                -1
            val d = Calendar.getInstance()
            d.add(Calendar.DAY_OF_YEAR, it)
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            val dateStr: String = sdf.format(d.time)
            val valueString = it.toString()
            val amount = factor * (it * 1000)
            val fee = (it * 10)
            val trans = TransactionModel(
                id = valueString,
                date = dateStr,
                description = "Description: $it",
                amount = amount.toString(),
                fee = fee.toString(),
                total = (amount + fee).toString()

            )
            listTransactions.add(trans)
        }
        return Result.success(listTransactions)
    }
}