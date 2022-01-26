package com.atsistemas.domain.common


import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Carlos Mateo Benito on 20/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */

fun String.formatDate(): String? {

    return if (isBlank()) {
        null
    } else {
        try {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                parser.parse(this)?.let {
                    formatter.format(it)
                }
        } catch (e: Exception) {
            null
        }
    }
}