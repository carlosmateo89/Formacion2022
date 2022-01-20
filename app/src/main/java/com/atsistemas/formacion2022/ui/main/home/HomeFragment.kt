package com.atsistemas.formacion2022.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.atsistemas.formacion2022.common.BaseFragment
import com.atsistemas.formacion2022.data.model.TransactionModel
import com.atsistemas.formacion2022.databinding.FragmentHomeBinding
import com.atsistemas.formacion2022.ui.dialog.DialogData
import com.atsistemas.formacion2022.ui.dialog.ErrorDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    private val dialogError by lazy { ErrorDialogFragment.newInstance() }
    private val vm by sharedViewModel<HomeViewModel>()

    private val homeAdapter by lazy {
        HomeAdapter(){
            vm.onActionTransactionClicked(it)
        }
    }
    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            rvHome.layoutManager =LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            rvHome.adapter = homeAdapter
            rvHome.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }
        vm.onInit()
        setupBinding()
    }

    private fun setupBinding() {
        observeData(vm.obsListTransactions,::onObserveList)
        observeData(vm.obsShowDialog,::onObserveDialogData)
        observeData(vm.obsShowMessage,::onObserveMessage)
    }

    private fun onObserveList(list: List<TransactionModel>) {
        homeAdapter.updateList(list)
    }


    private fun onObserveMessage(message: String) {
       Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }


    private fun onObserveDialogData(dialogData: DialogData) {
        if(dialogData.show){
            dialogError.show(parentFragmentManager,HomeFragment::class.java.name,dialogData.description){
                vm.onActionErrorAcceptClicked()
            }
        }
        else{
            dialogError.dismiss(parentFragmentManager)
        }
    }
}