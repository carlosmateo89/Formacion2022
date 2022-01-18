package com.atsistemas.formacion2022.data.model

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
data class TransactionModel(
    val id:String,
    val date:String,
    val amount:String,
    val description:String?,
    val fee:String?,
    val total:String?
)