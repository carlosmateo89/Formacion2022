package com.atsistemas.formacion2022.data.utils

import com.atsistemas.formacion2022.data.common.formatDate
import com.atsistemas.formacion2022.data.model.TransactionModel

/**
 * Created by Carlos Mateo Benito on 20/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */

object UtilsTransaction{

    fun filterInvalidDateTransactions(list: List<TransactionModel>): List<TransactionModel>{
        //Return list without transactions with null dates
        val filteredList = mutableListOf<TransactionModel>()
        list.forEach { transaction->
            transaction.date.formatDate()?.also { 
                filteredList.add(transaction.copy(date = it))
            }
            
        }
        return filteredList
    }

    fun orderByDescending(list: List<TransactionModel>): List<TransactionModel>{
        return list.sortedByDescending { it.date }
    }
}
