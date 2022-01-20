package com.atsistemas.formacion2022.data.common

import android.os.Build
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val instantTime = Instant.parse(this)
                val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                outputFormatter.format(instantTime.atZone(ZoneId.systemDefault()).toLocalDate())
            } else {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                parser.parse(this)?.let {
                    formatter.format(it)
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}