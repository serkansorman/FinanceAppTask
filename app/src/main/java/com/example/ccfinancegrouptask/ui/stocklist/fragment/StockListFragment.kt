package com.example.ccfinancegrouptask.ui.stocklist.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.data.model.StockModel
import com.example.ccfinancegrouptask.databinding.FragmentStockListBinding
import com.example.ccfinancegrouptask.ui.stocklist.adapter.StockListAdapter

class StockListFragment : BaseFragment() {

    private lateinit var binding: FragmentStockListBinding
    private lateinit var adapter: StockListAdapter

    private val onStockClick: () -> Unit = {
        findNavController().navigate(
            StockListFragmentDirections.actionStockListFragmentToStockDescriptionFragment()
        )
    }

    override fun initDI() {
        TODO("Not yet implemented")
    }

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentStockListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        initAdapter()
        initSearchLayout()
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }

    private fun initAdapter(){

        adapter = StockListAdapter(onStockClick)
        binding.recyclerView.adapter = adapter


        val list = mutableListOf<StockModel>().apply {
            repeat(20) {
                add(
                    StockModel(
                        "BTC-USD",
                        "CCC",
                        "CCC",
                        "CRYPTO",
                        "29084.479",
                        "29084.479"
                    )
                )

                add(
                    StockModel(
                        "APPLE",
                        "APL",
                        "APPLE INC",
                        "STOCK",
                        "1234.479",
                        "1234.479"
                    )
                )
            }
        }


        adapter.updateStockList(list)
    }

    private fun initSearchLayout(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }
        })
    }
}