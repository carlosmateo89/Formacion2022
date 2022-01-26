package com.atsistemas.formacion2022.data.remote.retrofit

import com.atsistemas.domain.model.TransactionModel
import com.google.gson.annotations.SerializedName

/**
 * Created by Carlos Mateo Benito on 26/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
data class TransactionDataRemote(
    @SerializedName("id")
    val id:String,
    @SerializedName("date")
    val date:String,
    @SerializedName("amount")
    val amount:String,
    @SerializedName("description")
    val description:String?,
    @SerializedName("fee")
    val fee:String?,
    @SerializedName("total")
    val total:String?
)

fun TransactionDataRemote.toDomain():TransactionModel{
    return TransactionModel(id, date, amount, description, fee, total)
}