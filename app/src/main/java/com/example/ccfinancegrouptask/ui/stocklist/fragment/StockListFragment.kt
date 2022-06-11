package com.example.ccfinancegrouptask.ui.stocklist.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.data.model.StockModel
import com.example.ccfinancegrouptask.databinding.FragmentStockListBinding
import com.example.ccfinancegrouptask.ui.stocklist.adapter.StockListAdapter

class StockListFragment : BaseFragment() {

    private lateinit var binding: FragmentStockListBinding
    private lateinit var adapter: StockListAdapter

    override fun initDI() {
        TODO("Not yet implemented")
    }

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentStockListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        adapter = StockListAdapter()
        binding.recyclerView.adapter = adapter


        val list = mutableListOf<StockModel>().apply {
            repeat(20){
                add(StockModel(
                    "BTC-USD",
                    "CCC",
                    "CCC",
                    "CRYPTO",
                    "29084.479",
                    "29084.479"
                ))
            }
        }



        adapter.updateStockList(list)

    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }
}