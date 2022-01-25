package com.atsistemas.formacion2022.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atsistemas.formacion2022.ui.dialog.DialogData
import com.atsistemas.formacion2022.ui.main.MainViewModel

/**
 * Created by Carlos Mateo Benito on 17/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
abstract class BaseViewModel : ViewModel() {

    protected val liveShowLoading = MutableLiveData<Boolean>()
    val obsShowLoading: LiveData<Boolean> = liveShowLoading

    protected val liveShowDialog = MutableLiveData<DialogData>()
    val obsShowDialog:LiveData<DialogData> = liveShowDialog

    protected val liveShowMessage = SingleLiveEvent<String>()
    val obsShowMessage:LiveData<String> = liveShowMessage

    protected val liveNavigation = SingleLiveEvent<NavData?>()
    val obsNavigate = liveNavigation

    protected lateinit var mainViewModel: MainViewModel

    fun attachMainViewModel(mainViewModel: MainViewModel){
        this.mainViewModel = mainViewModel
    }

    protected fun showLoading(){
        liveShowLoading.value = true
    }

    protected fun hideLoading(){
        liveShowLoading.value = false
    }

    protected fun showMessage(message:String){
        liveShowMessage.value = message
    }

    protected fun showDialog(dialogData: DialogData){
        liveShowDialog.value = dialogData
    }

    open fun onActionErrorAcceptClicked() {
        liveShowDialog.value = DialogData(show = false)
    }

    protected fun navigate(navData: NavData){
        liveNavigation.value = navData
    }

}