package com.atsistemas.formacion2022.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atsistemas.domain.model.TransactionModel
import com.atsistemas.formacion2022.R
import com.atsistemas.formacion2022.common.getColor
import com.atsistemas.formacion2022.common.getString
import com.atsistemas.formacion2022.databinding.ItemHomeBinding

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
class HomeAdapter(
    private val listener : (TransactionModel)->Unit)
    : ListAdapter<TransactionModel,HomeAdapter.HomeTransactionViewHolder>(object:
    DiffUtil.ItemCallback<TransactionModel>() {

    override fun areItemsTheSame(oldItem: TransactionModel, newItem: TransactionModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TransactionModel, newItem: TransactionModel): Boolean {
        return oldItem.id == newItem.id
    }

}) {

    inner class HomeTransactionViewHolder(val binding : ItemHomeBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(transactionModel: TransactionModel){
            val context = itemView.context
            with(binding){
                transactionModel.also {
                    tvItemHomeDate.text = it.date
                    tvItemHomeAmount.text = it.amount
                    tvItemHomeDescription.text = it.description
                    tvItemHomeFee.text = it.fee

                    kotlin.runCatching {
                        if(it.amount.toFloat()>=0f) {
                            tvItemHomeAmount.setTextColor(R.color.teal_700.getColor(context))
                            ivItemHomeAmount.setImageResource(R.drawable.ic_in)
                        }
                        else {
                            tvItemHomeAmount.setTextColor(R.color.red.getColor(context))
                            ivItemHomeAmount.setImageResource(R.drawable.ic_out)
                        }

                        it.fee?.toFloat()?.also { _fee ->
                            tvItemHomeFee.text = R.string.home_fee.getString(context,_fee)
                        }
                    }
                }


            }
            itemView.setOnClickListener {
                listener(transactionModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTransactionViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeTransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeTransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}