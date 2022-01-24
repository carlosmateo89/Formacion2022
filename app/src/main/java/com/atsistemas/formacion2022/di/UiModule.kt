package com.atsistemas.formacion2022.di

import com.atsistemas.formacion2022.ui.main.MainViewModel
import com.atsistemas.formacion2022.ui.main.detail.DetailViewModel
import com.atsistemas.formacion2022.ui.main.home.HomeViewModel
import com.atsistemas.formacion2022.ui.main.menu.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
val uiModule = module {

    viewModel{
        HomeViewModel(get())
    }

    viewModel{
        DetailViewModel()
    }

    viewModel{
        MainViewModel()
    }

    viewModel{
        MenuViewModel()
    }


}