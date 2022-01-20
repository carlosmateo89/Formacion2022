package com.atsistemas.formacion2022.data.repository

import androidx.lifecycle.LiveData
import com.atsistemas.formacion2022.data.database.AppDatabase
import com.atsistemas.formacion2022.data.model.TransactionModel
import com.atsistemas.formacion2022.data.remote.ResultHandler
import com.atsistemas.formacion2022.data.remote.TransactionAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

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

    suspend fun getTransactionsRemotely(): Response<List<TransactionModel>> {
        return transactionAPI.getTransactions()
    }

    suspend fun updateTransactions(): ResultHandler<String>{
        return withContext(Dispatchers.IO){
            val result = safeApiCall {
                transactionAPI.getTransactions()
            }
            val resultFormatted = when(result){
                is ResultHandler.GenericError ->{
                    result
                }
                is ResultHandler.HttpError -> {
                    result
                }
                is ResultHandler.NetworkError -> {
                    result
                }
                is ResultHandler.Success -> {
                    val transactions = result.data
                    saveTransactions(*transactions.toTypedArray())
                    ResultHandler.Success("Success")
                }
            }
            resultFormatted
        }
    }

    suspend fun saveTransactions(vararg transactionModel: TransactionModel) {
        db.transactionsDao().saveTransactions(*transactionModel)
    }

    fun getTransactionsLocally(): LiveData<List<TransactionModel>> {
        return db.transactionsDao().getTransactions()
    }

    suspend fun deleteTransactions() {
        withContext(Dispatchers.IO) {
            db.transactionsDao().deleteTransactions()
        }
    }

    private suspend fun <T> safeApiCall(call: suspend () -> retrofit2.Response<T>): ResultHandler<T> {
        return try {
            val response = call()
            when {
                response.isSuccessful -> {
                    ResultHandler.Success(response.body() as T)
                }
                else -> {
                    ResultHandler.HttpError(response.code(), "Http error")
                }
            }
        } catch (e: Throwable) {
            when (e) {
                is IOException -> ResultHandler.NetworkError()
                else -> ResultHandler.GenericError(e.message ?: "")
            }
        }
    }
}