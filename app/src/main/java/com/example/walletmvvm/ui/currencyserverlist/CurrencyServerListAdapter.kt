package com.example.walletmvvm.ui.currencyserverlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walletmvvm.R
import com.example.walletmvvm.data.model.CurrencyModel
import kotlinx.android.synthetic.main.item_currencyserverlist.view.*

class CurrencyServerListAdapter(private val callbackInterface: ViewCallbackInterface) :
    RecyclerView.Adapter<CurrencyServerListAdapter.ViewHolder>() {

    var currencyList = emptyList<CurrencyModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_currencyserverlist,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = currencyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mCurrencyName.text = currencyList[position].name
        holder.mCurrencySymbol.text = currencyList[position].symbol
        holder.mCurrencyCode.text = currencyList[position].code
        holder.mAddButton.setOnClickListener {
            callbackInterface.insertCurrencyItemToDatabase(currencyList[position])
            callbackInterface.showResultCallback("item added")
        }

    }

    fun setData(currencyList: List<CurrencyModel>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mCurrencyName: TextView by lazy { itemView.textview_currencylist_symbol }
        val mCurrencySymbol: TextView by lazy { itemView.textView_currencylist_name }
        val mCurrencyCode: TextView by lazy { itemView.textView_currencylist_code }
        val mAddButton: ImageButton by lazy { itemView.imagebutton_currencylist_add }

    }

    interface ViewCallbackInterface {
        fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel)
        fun showResultCallback(message: String)
    }
}