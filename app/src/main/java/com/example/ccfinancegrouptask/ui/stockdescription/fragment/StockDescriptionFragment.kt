package com.example.ccfinancegrouptask.ui.stockdescription.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.databinding.FragmentStockDescriptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockDescriptionFragment : BaseFragment() {

    private lateinit var binding: FragmentStockDescriptionBinding

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentStockDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initUI() {
        binding.apply {
            symbol.text = "addadaadsas"
            shortName.text = "dfdsffssfs"
            longName.text = "aadaada"
            price.text = "356454616"

        }
    }

    override fun initObservers() {
    }

    override fun fetchData() {
    }

}