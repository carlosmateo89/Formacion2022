package com.atsistemas.formacion2022.ui.main

import androidx.lifecycle.MutableLiveData
import com.atsistemas.formacion2022.common.BaseViewModel

/**
 * Created by Carlos Mateo Benito on 24/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class MainViewModel : BaseViewModel() {

    private val liveShowFab = MutableLiveData<Boolean>()
    val obsShowFab = liveShowFab



    fun showFab(show:Boolean){
        liveShowFab.value = show
    }

}