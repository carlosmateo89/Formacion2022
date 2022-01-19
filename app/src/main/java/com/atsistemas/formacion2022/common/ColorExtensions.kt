package com.atsistemas.formacion2022.common

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * Created by Carlos Mateo Benito on 19/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
fun Int.getColor(context: Context):Int{
    return context.getColor(this)
}