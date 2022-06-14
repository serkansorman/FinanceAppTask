package com.example.ccfinancegrouptask.ui.stockdescription.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.databinding.FragmentStockDescriptionBinding
import com.example.ccfinancegrouptask.ui.stockdescription.event.StockDescriptionEvent
import com.example.ccfinancegrouptask.ui.stocklist.viewmodel.StockListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StockDescriptionFragment : BaseFragment() {

    private lateinit var binding: FragmentStockDescriptionBinding
    private val viewModel by viewModel<StockListViewModel>()
    private val args: StockDescriptionFragmentArgs by navArgs()

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentStockDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun initUI() {

    }

    fun initDetailUI(stockDescription: StockDescriptionResponseModel) {
        binding.apply { //TODO change rate color
            stockDescription.summary?.apply {
                textStockName.text = shortName
                textStockCurrency.text = currency
                textStockPrice.text = regularMarketPrice?.fmt
                textStockChangeRate.text = regularMarketChangePercent?.fmt
                textStockOpenPrice.text = regularMarketOpen?.fmt
                textStockClosePrice.text = regularMarketPreviousClose?.fmt
                textStockLow.text = regularMarketDayLow?.fmt
                textStockHigh.text = regularMarketDayHigh?.fmt
            }
        }
    }

    override fun initObservers() {
        lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.stockDescriptionFlow.collect{
                    when(it){
                        is StockDescriptionEvent.Success -> {
                            initDetailUI(it.stockDescription)
                        }
                        is StockDescriptionEvent.Failure -> {
                            //TODO handle error
                        }
                    }

                }
            }
        }
    }

    override fun fetchData() {
        viewModel.getStockDescription(args.stockSymbol)
    }

    /*TODO override fun onDestroy() {
    mBinding = null
    super.onDestroy()
}*/

}