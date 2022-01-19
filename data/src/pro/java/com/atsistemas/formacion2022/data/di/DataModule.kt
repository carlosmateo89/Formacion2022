package com.atsistemas.formacion2022.data.di

import com.atsistemas.formacion2022.data.BuildConfig
import com.atsistemas.formacion2022.data.remote.MockInterceptor
import com.atsistemas.formacion2022.data.remote.TransactionAPI
import com.atsistemas.formacion2022.data.repository.TransactionRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
val dataModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .writeTimeout( 60,TimeUnit.SECONDS)
           // .addInterceptor(MockInterceptor(get()))
            .build()
    }

    single <TransactionAPI>{
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TransactionAPI::class.java)

    }

    single {
       TransactionRepository(get())
    }
}