package com.example.ccfinancegrouptask.ui.stocklist.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.databinding.FragmentStockListBinding
import com.example.ccfinancegrouptask.ui.stockdescription.fragment.StockDescriptionFragmentArgs
import com.example.ccfinancegrouptask.ui.stocklist.adapter.StockListAdapter
import com.example.ccfinancegrouptask.ui.stocklist.event.StockListEvent
import com.example.ccfinancegrouptask.ui.stocklist.viewmodel.StockListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StockListFragment : BaseFragment() {

    private lateinit var binding: FragmentStockListBinding
    private val viewModel by viewModel<StockListViewModel>()
    private lateinit var adapter: StockListAdapter

    private val onStockClick: (stockSymbol: String?) -> Unit = {
        findNavController().navigate(
            StockListFragmentDirections.actionStockListFragmentToStockDescriptionFragment(it)
        )
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
        lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.stockListStateFlow.collect{
                    when(it){
                        is StockListEvent.Success -> {
                            adapter.updateStockList(it.stockList.toMutableList())
                        }
                        is StockListEvent.Failure -> {
                            //TODO handle error
                        }
                    }

                }
            }
        }
    }

    override fun fetchData() {
        viewModel.getStockList()
    }

    private fun initAdapter(){
        adapter = StockListAdapter(onStockClick)
        binding.recyclerView.adapter = adapter
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

    /*TODO override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }*/
}