package com.atsistemas.formacion2022.common

import android.app.Application
import com.atsistemas.formacion2022.data.di.dataModule
import com.atsistemas.formacion2022.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Carlos Mateo Benito on 17/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class FormacionApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FormacionApplication)
            modules(
                uiModule,
                dataModule
            )
        }

    }
}