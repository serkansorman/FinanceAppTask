package com.example.ccfinancegrouptask.ui.stocklist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccfinancegrouptask.data.model.StockModel
import com.example.ccfinancegrouptask.databinding.RowStockListBinding

class StockListAdapter(private val onStockClick: () -> Unit) : RecyclerView.Adapter<StockListAdapter.StockViewHolder>() {

    private var stockList = mutableListOf<StockModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        return StockViewHolder(
            RowStockListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stockList[position])
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    fun updateStockList(stockList: MutableList<StockModel>) {
        this.stockList.apply {
            clear()
            addAll(stockList)
        }
        notifyDataSetChanged()
    }

    inner class StockViewHolder(private val binding: RowStockListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stockModel: StockModel) {
            binding.apply {
                textStockSymbol.text = stockModel.symbol
                textStockLongName.text = stockModel.fullExchangeName
                textStockShortName.text = stockModel.exchange
                textStockPrice.text = stockModel.previousClose
                layoutStock.setOnClickListener { onStockClick.invoke() }
            }
        }
    }
}