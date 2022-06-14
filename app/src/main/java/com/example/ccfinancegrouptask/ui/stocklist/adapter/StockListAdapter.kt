package com.example.ccfinancegrouptask.ui.stocklist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.ccfinancegrouptask.data.model.StockModel
import com.example.ccfinancegrouptask.databinding.RowStockListBinding

class StockListAdapter(private val onStockClick: (String?) -> Unit) :
    RecyclerView.Adapter<StockListAdapter.StockViewHolder>(), Filterable {

    private var stockList = mutableListOf<StockModel>()
    private var filteredStockList = mutableListOf<StockModel>()

    init {
        filteredStockList = stockList
    }

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
        holder.bind(filteredStockList[position])
    }

    override fun getItemCount(): Int {
        return filteredStockList.size
    }

    fun updateStockList(stockList: MutableList<StockModel>) {
        this.filteredStockList.apply {
            clear()
            addAll(stockList)
        }
        notifyDataSetChanged()
    }

    inner class StockViewHolder(private val binding: RowStockListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stockModel: StockModel) {
            with(binding) {
                textStockExchange.text = stockModel.exchange
                textStockLongName.text = stockModel.fullExchangeName
                textStockShortName.text = stockModel.shortName
                textStockPrice.text = stockModel.marketClosePrice?.fmt
                layoutStock.setOnClickListener { onStockClick(stockModel.symbol) }
            }
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString()
                filteredStockList = if (query.isEmpty()) {
                    stockList
                } else {
                    stockList.filter { stock -> isStockMatched(stock, query) }.toMutableList()
                }
                return FilterResults().apply {
                    values = filteredStockList
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredStockList = results?.values as MutableList<StockModel>
                notifyDataSetChanged()
            }
        }
    }

    private fun isStockMatched(stockModel: StockModel, query: String): Boolean {
        return query.isNotBlank() && (stockModel.shortName?.lowercase()?.contains(query.lowercase()) == true ||
                stockModel.fullExchangeName?.lowercase()?.contains(query.lowercase()) == true ||
                stockModel.exchange?.lowercase()?.contains(query.lowercase()) == true)
    }
}