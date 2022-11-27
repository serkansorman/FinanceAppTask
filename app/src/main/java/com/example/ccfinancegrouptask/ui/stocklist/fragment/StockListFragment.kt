package com.example.ccfinancegrouptask.ui.stocklist.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.databinding.FragmentStockListBinding
import com.example.ccfinancegrouptask.ui.stockdescription.viewmodel.StockListViewModel
import com.example.ccfinancegrouptask.ui.stocklist.adapter.StockListAdapter
import com.example.ccfinancegrouptask.ui.stocklist.event.StockListEvent
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
        setOnErrorRefreshClick()
    }

    override fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stockListFlow.collect {
                    when (it) {
                        is StockListEvent.Success -> {
                            endLoading()
                            adapter.updateStockList(it.stockList.toMutableList())
                        }
                        is StockListEvent.Failure -> {
                            endLoading()
                            showErrorHandlerLayout()
                        }
                        is StockListEvent.Loading -> {
                            startLoading()
                        }
                    }
                }
            }
        }
    }

    override fun fetchData() {
        viewModel.refreshStockList()
    }

    private fun initAdapter() {
        adapter = StockListAdapter(onStockClick)
        binding.recyclerView.adapter = adapter
    }

    private fun initSearchLayout() {
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

    override fun startLoading() {
        binding.apply {
            recyclerView.isVisible = false
            loading.progressBar.isVisible = true
        }
    }

    override fun endLoading() {
        binding.apply {
            recyclerView.isVisible = true
            loading.progressBar.isVisible = false
        }
    }

    override fun showErrorHandlerLayout() {
        binding.error.layout.isVisible = true
    }

    override fun hideErrorHandlerLayout() {
        binding.error.layout.isVisible = false
    }

    override fun setOnErrorRefreshClick() {
        binding.error.refreshButton.setOnClickListener {
            hideErrorHandlerLayout()
            fetchData()
        }
    }
}