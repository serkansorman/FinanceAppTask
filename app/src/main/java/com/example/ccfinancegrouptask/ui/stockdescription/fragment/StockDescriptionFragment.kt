package com.example.ccfinancegrouptask.ui.stockdescription.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.databinding.FragmentStockDescriptionBinding
import com.example.ccfinancegrouptask.domain.model.StockDescriptionUIModel
import com.example.ccfinancegrouptask.ui.stockdescription.event.StockDescriptionEvent
import com.example.ccfinancegrouptask.ui.stocklist.viewmodel.StockDescriptionViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StockDescriptionFragment : BaseFragment() {

    private lateinit var binding: FragmentStockDescriptionBinding
    private val viewModel by viewModel<StockDescriptionViewModel>()
    private val args: StockDescriptionFragmentArgs by navArgs()

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentStockDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        setOnErrorRefreshClick()
    }

    private fun initDetailUI(stockDescription: StockDescriptionUIModel) {
        binding.apply {
            stockDescription.apply {
                textStockName.text = shortName
                textStockCurrency.text = currency
                textStockPrice.text = lastPrice
                textStockChangeRate.text = changePercent
                textStockOpenPrice.text = openPrice
                textStockClosePrice.text = closePrice
                textStockLow.text = priceLow
                textStockHigh.text = priceHigh
            }
        }
    }

    override fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.stockDescriptionFlow.collect {
                    when (it) {
                        is StockDescriptionEvent.Success -> {
                            endLoading()
                            initDetailUI(it.stockDescription)
                        }
                        is StockDescriptionEvent.Failure -> {
                            endLoading()
                            showErrorHandlerLayout()
                        }
                        is StockDescriptionEvent.Loading -> {
                            startLoading()
                        }
                    }
                }
            }
        }
    }

    override fun fetchData() {
        viewModel.getStockDescription(args.stockSymbol)
    }

    override fun startLoading() {
        binding.apply {
            loading.progressBar.isVisible = true
            layoutStockDescription.isVisible = false
        }
    }

    override fun endLoading() {
        binding.apply {
            loading.progressBar.isVisible = false
            layoutStockDescription.isVisible = true
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