package com.atsistemas.formacion2022.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.atsistemas.formacion2022.R
import com.atsistemas.formacion2022.common.BaseDialogFragment
import com.atsistemas.formacion2022.databinding.FragmentDialogErrorBinding

/**
 * Created by Carlos Mateo Benito on 20/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class ErrorDialogFragment : BaseDialogFragment<FragmentDialogErrorBinding>() {

    companion object{
        fun newInstance(): ErrorDialogFragment{
            return ErrorDialogFragment()
        }
    }

    private var acceptListener: (()->Unit)?=null
    private var message:String?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvDialogErrorDescription.text
    }


    /*
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_eror_title)
            .setMessage(R.string.dialog_error_message)
            .setOnDismissListener {

            }
            .setPositiveButton(R.string.dialog_error_accept){ _, _ ->

            }
            .create()
    }
    */


    override fun provideBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDialogErrorBinding {
        return FragmentDialogErrorBinding.inflate(inflater,container,false)
    }
}