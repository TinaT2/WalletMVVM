package com.example.walletmvvm.ui.currencylocallist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.databinding.ItemCurrencylocallistBinding

class CurrencyLocalListAdapter(var currencyList: List<CurrencyModel>) :
    RecyclerView.Adapter<CurrencyLocalListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val recyclerCurrencyLocalListBinding =
            ItemCurrencylocallistBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(recyclerCurrencyLocalListBinding.root, recyclerCurrencyLocalListBinding)
    }
//       past: ViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.item_currencylocallist,
//                parent,
//                false
//            )
//        )

    override fun getItemCount(): Int = currencyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//       past: holder.itemView.textView_currencylist_name.text = currencyList[position].name
//        holder.itemView.textview_currencylist_symbol.text = currencyList[position].symbol
//        holder.itemView.textView_currencylist_code.text = currencyList[position].code

        holder.setData(currencyList[position])
    }

    fun setData(currencyList: List<CurrencyModel>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    class ViewHolder(
        itemView: View, private val recyclerItemDogListBinding:
        ItemCurrencylocallistBinding
    ) : RecyclerView.ViewHolder(itemView) {
        fun setData(currencyModel: CurrencyModel) {
            recyclerItemDogListBinding.currencyModel = currencyModel
        }
    }

}