package com.example.ccfinancegrouptask.ui.stockdescription.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.ccfinancegrouptask.base.BaseFragment
import com.example.ccfinancegrouptask.databinding.FragmentStockDescriptionBinding
import com.example.ccfinancegrouptask.ui.stockdescription.event.StockDescriptionEvent
import com.example.ccfinancegrouptask.ui.stocklist.event.StockListEvent
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

    override fun initObservers() {
        lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.stockDescriptionFlow.collect{
                    when(it){
                        is StockDescriptionEvent.Success -> {
                            binding.apply {
                                symbol.text = it.stockDescription.data?.shortName
                                shortName.text = it.stockDescription.data?.longName
                                longName.text = it.stockDescription.data?.exchangeName
                                price.text = it.stockDescription.data?.currency

                            }
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