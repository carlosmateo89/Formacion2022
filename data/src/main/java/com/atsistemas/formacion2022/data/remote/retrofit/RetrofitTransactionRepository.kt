package com.atsistemas.formacion2022.data.remote.retrofit

import com.atsistemas.domain.exception.TransactionExceptions
import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.domain.repository.GetTransactionRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Carlos Mateo Benito on 26/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class RetrofitTransactionRepository : GetTransactionRepository {


    private val retrofit: Retrofit =
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()
            )
            .baseUrl(com.atsistemas.formacion2022.data.BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val transactionAPI = retrofit.create(TransactionAPI::class.java)

    override suspend fun getTransactions(): Result<List<TransactionModel>> {
        return safeApiCall {
            transactionAPI.getTransactions()
        }.map {
            it.map {
                it.toDomain()
            }
        }
    }

    private suspend fun <T> safeApiCall(call: suspend () -> retrofit2.Response<T>): Result<T> {
        return try {
            val response = call()
            when {
                response.isSuccessful -> {
                    Result.success(response.body() as T)
                }
                else -> {
                    Result.failure(TransactionExceptions.HttpError(response.code(), "Http error"))
                }
            }
        } catch (e: Throwable) {
            when (e) {
                is IOException -> Result.failure(TransactionExceptions.NetworkError())
                else -> Result.failure(TransactionExceptions.GenericError(e.message ?: ""))
            }
        }
    }
}