package com.atsistemas.formacion2022.data.remote

import com.atsistemas.formacion2022.data.model.TransactionModel
import retrofit2.http.GET

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
interface TransactionAPI {

    @GET("transactions.json")
    suspend fun getTransactions():List<TransactionModel>


}