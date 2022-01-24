package com.atsistemas.formacion2022.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.atsistemas.formacion2022.data.model.TransactionModel
import com.atsistemas.formacion2022.ui.dialog.DialogData
import com.atsistemas.formacion2022.ui.dialog.ErrorDialogFragment
import com.atsistemas.formacion2022.ui.main.MainActivity
import com.atsistemas.formacion2022.ui.main.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Carlos Mateo Benito on 17/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
abstract class BaseFragment<T:ViewBinding,VM:BaseViewModel> : Fragment() {

    private val dialogError by lazy { ErrorDialogFragment.newInstance() }

    private var _binding  : T? = null

    protected val binding
        get() =  _binding!!

    protected abstract val vm:VM

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = provideBinding(inflater,container)
        return binding.root
    }


    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData(vm.obsShowLoading,::onObserveLoading)
        observeData(vm.obsShowDialog,::onObserveDialogData)
        observeData(vm.obsShowMessage,::onObserveMessage)
        observeData(vm.obsNavigate,::onObserveNavigation)

    }

    protected open fun onObserveNavigation(navData: NavData) {}

    private fun onObserveLoading(show: Boolean) {
            (requireActivity() as? MainActivity)?.also {
                if(show)
                    it.showLoading()
                else
                    it.hideLoading()
            }
    }

    abstract fun provideBinding(inflater: LayoutInflater, container: ViewGroup?):T

    private fun onObserveMessage(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }


    private fun onObserveDialogData(dialogData: DialogData) {
        if(dialogData.show){
            dialogError.show(parentFragmentManager, HomeFragment::class.java.name,dialogData.description){
                vm.onActionErrorAcceptClicked()
            }
        }
        else{
            dialogError.dismiss(parentFragmentManager)
        }
    }


    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun <LD>observeData(liveData: LiveData<LD>,action:(LD)->Unit){
        liveData.observe(viewLifecycleOwner){
            action.invoke(it)
        }
    }


}