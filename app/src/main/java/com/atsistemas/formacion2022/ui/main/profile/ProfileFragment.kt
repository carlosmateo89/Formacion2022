package com.atsistemas.formacion2022.ui.main.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.atsistemas.formacion2022.common.BaseFragment
import com.atsistemas.formacion2022.common.setText
import com.atsistemas.formacion2022.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Carlos Mateo Benito on 25/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val vm: ProfileViewModel by viewModel()

    override fun provideBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater, container, false)
    }

    private val nameTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(text: Editable?) {
            vm.onActionNameWritten(text.toString())
        }

    }

    private val surnameTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(text: Editable?) {
            vm.onActionSurnameWritten(text.toString())
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            etProfileName.addTextChangedListener(nameTextWatcher)
            etProfileSurname.addTextChangedListener(surnameTextWatcher)
        }

        observeData(vm.obsName,::onObserveName)
        observeData(vm.obsSurname,::onObserveSurname)
    }

    private fun onObserveName(name: String) {
        binding.tvProfileSavedName.text = name
        binding.etProfileName.setText(name,nameTextWatcher)
    }

    private fun onObserveSurname(surname: String) {
        binding.tvProfileSavedSurname.text = surname
        binding.etProfileSurname.setText(surname,surnameTextWatcher)
    }

}