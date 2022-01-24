package com.atsistemas.formacion2022.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atsistemas.formacion2022.BuildConfig
import com.atsistemas.formacion2022.R
import com.atsistemas.formacion2022.common.BaseFragment
import com.atsistemas.formacion2022.common.NavData
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
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {

    override val vm: HomeViewModel by sharedViewModel()

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
        setupRecycler()
        vm.onInit()
        setupBinding()
    }

    private fun setupRecycler() {
        with(binding) {
            rvHome.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvHome.adapter = homeAdapter
            rvHome.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            val deleteHelper = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    vm.onActionOnItemSwiped(viewHolder.adapterPosition)
                }

            }
            ItemTouchHelper(deleteHelper).attachToRecyclerView(rvHome)
        }
    }

    override fun onObserveNavigation(navData: NavData) {
        when(navData.id){
            HomeViewModel.NAV_DETAIL ->{

                val transaction = navData.data as TransactionModel
                /*val bundle = Bundle().apply {
                    putSerializable(TransactionModel::class.java.name,transaction)
                }
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
*/
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(transaction))
            }
        }
    }


    private fun setupBinding() {
        observeData(vm.obsListTransactions,::onObserveList)
    }

    private fun onObserveList(list: List<TransactionModel>) {
        homeAdapter.updateList(list)
    }
}