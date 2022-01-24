package com.atsistemas.formacion2022.ui.main.menu

import com.atsistemas.formacion2022.common.BaseViewModel
import com.atsistemas.formacion2022.common.NavData

/**
 * Created by Carlos Mateo Benito on 24/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class MenuViewModel : BaseViewModel() {

    companion object{
        const val NAV_PROFILE = 0
        const val NAV_TRANSACTION = 1
    }

    fun onActionTransactionClicked() {
        navigate(NavData(NAV_TRANSACTION))
    }

    fun onActionProfileClicked() {
        navigate(NavData(NAV_PROFILE))
    }
}