package com.example.walletmvvm.ui.currencylocallist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walletmvvm.R
import com.example.walletmvvm.data.model.CurrencyModel
import kotlinx.android.synthetic.main.item_currencylocallist.view.*

class CurrencyLocalListAdapter :
    RecyclerView.Adapter<CurrencyLocalListAdapter.ViewHolder>() {

    var currencyList = emptyList<CurrencyModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_currencylocallist,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = currencyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textView_currencylist_name.text = currencyList[position].name
        holder.itemView.textview_currencylist_symbol.text = currencyList[position].symbol
        holder.itemView.textView_currencylist_code.text = currencyList[position].code

    }

    fun setData(currencyList: List<CurrencyModel>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}