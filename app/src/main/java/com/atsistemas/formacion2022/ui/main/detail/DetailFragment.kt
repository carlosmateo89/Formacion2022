package com.atsistemas.formacion2022.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.atsistemas.formacion2022.common.BaseFragment
import com.atsistemas.formacion2022.data.model.TransactionModel
import com.atsistemas.formacion2022.databinding.FragmentDetailBinding
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
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val vm: DetailViewModel by viewModel()

    val args: DetailFragmentArgs by navArgs()

    override fun provideBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val transaction = arguments?.getSerializable(TransactionModel::class.java.name) as? TransactionModel
        val transaction = args.transaction
        vm.onInit(transaction)

        observeData(vm.obsTransaction,::onObserveTransaction)
    }

    private fun onObserveTransaction(transactionModel: TransactionModel) {
        binding.tvDetailTitle.text = transactionModel.date
        binding.tvDetailDescription.text = transactionModel.amount
    }
}