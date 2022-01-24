package com.atsistemas.formacion2022.ui.main.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.atsistemas.formacion2022.common.BaseFragment
import com.atsistemas.formacion2022.common.NavData
import com.atsistemas.formacion2022.databinding.FragmentMenuBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Carlos Mateo Benito on 24/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class MenuFragment : BaseFragment<FragmentMenuBinding,MenuViewModel>() {

    override val vm: MenuViewModel by viewModel()

    override fun provideBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding.apply {
            bMenuProfile.setOnClickListener {
                vm.onActionProfileClicked()
            }
            bMenuTransactions.setOnClickListener {
                vm.onActionTransactionClicked()
            }
        }
    }

    override fun onNavigate(navData: NavData) {
       when(navData.id){
            MenuViewModel.NAV_PROFILE->{

            }
            MenuViewModel.NAV_TRANSACTION->{
                findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToHomeFragment())
            }
        }
    }
}