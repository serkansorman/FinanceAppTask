package com.example.ccfinancegrouptask.ui.stocklist.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.databinding.FragmentStockDescriptionBinding

class StockListFragment : BaseFragment() {

    private lateinit var binding: FragmentStockDescriptionBinding

    override fun initDI() {
        TODO("Not yet implemented")
    }

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentStockDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initUI() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }
}