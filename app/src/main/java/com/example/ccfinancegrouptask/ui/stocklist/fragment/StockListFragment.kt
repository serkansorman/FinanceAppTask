package com.example.ccfinancegrouptask.ui.stocklist.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.data.model.StockModel
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.example.ccfinancegrouptask.data.repository.StockRepositoryImpl
import com.example.ccfinancegrouptask.databinding.FragmentStockListBinding
import com.example.ccfinancegrouptask.ui.stocklist.adapter.StockListAdapter
import com.example.ccfinancegrouptask.ui.stocklist.viewmodel.StockListViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class StockListFragment : BaseFragment() {

    private lateinit var binding: FragmentStockListBinding
    private val viewModel by viewModels<StockListViewModel>()
    private lateinit var adapter: StockListAdapter

    private val onStockClick: () -> Unit = {
        findNavController().navigate(
            StockListFragmentDirections.actionStockListFragmentToStockDescriptionFragment()
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
                    adapter.updateStockList(it.data.result.toMutableList())
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