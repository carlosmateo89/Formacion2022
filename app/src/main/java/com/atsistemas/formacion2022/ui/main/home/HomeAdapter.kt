package com.atsistemas.formacion2022.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atsistemas.formacion2022.data.model.TransactionModel
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
class HomeAdapter(private val transactionList:List<TransactionModel> = emptyList()): RecyclerView.Adapter<HomeAdapter.HomeTransactionViewHolder>() {

    private var mutableTransactionList:MutableList<TransactionModel> = mutableListOf(*transactionList.toTypedArray())

    class HomeTransactionViewHolder(val binding : ItemHomeBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(transactionModel:TransactionModel){
            with(binding){
                transactionModel.also {
                    tvItemHomeDate.text = it.date
                    tvItemHomeAmount.text = it.amount
                    tvItemHomeDescription.text = it.description
                    tvItemHomeFee.text = it.fee
                }


            }
        }
    }

    fun updateList(list:List<TransactionModel>){
        mutableTransactionList.clear()
        mutableTransactionList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTransactionViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeTransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeTransactionViewHolder, position: Int) {
        holder.bind(mutableTransactionList[position])
    }

    override fun getItemCount(): Int {
        return mutableTransactionList.size
    }
}