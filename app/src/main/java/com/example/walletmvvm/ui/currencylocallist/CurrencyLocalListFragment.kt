package com.example.walletmvvm.ui.currencylocallist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walletmvvm.R
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.viewmodels.CurrencyViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_currencylocallist.*
import android.R.attr.data



class CurrencyLocalListFragment : Fragment() {

    private lateinit var currencyListAdapter: CurrencyLocalListAdapter

    private lateinit var currencyViewModel: CurrencyViewModel

    private var currencyList = emptyList<CurrencyModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate(inflater,R.layout.fragment_currencylocallist, container, false)
        val view = binding.getRoot()
        //here data must be an instance of the class MarsDataProvider
        binding.setMarsdata(data)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
    }

    private fun firstSetup() {
        //
        currencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        //
        //adapter
        recyclerview_currencylist_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        currencyListAdapter = CurrencyLocalListAdapter()
        recyclerview_currencylist_list.adapter = currencyListAdapter

        getCurrenciesFromDatabase()
    }


    private fun showResult(result: String) {
        constraintlayout_currencylist_base?.let { Snackbar.make(it, result, Snackbar.LENGTH_LONG).show() }
    }

    private fun getCurrenciesFromDatabase() {
        currencyViewModel.currencyList.observe(this, Observer { currencyList ->
            // Update the cached copy of the words in the adapter.
            currencyList?.let {
                for (currency in it) {
                    Log.v("currencyList", currency.name ?: " ")
                }
                showResult(it.size.toString() + " items")
                setRecyclerData(it)
            }
        })
    }

    private fun setRecyclerData(currencyList: List<CurrencyModel>) {
        progressbar_currencylist_progress?.visibility = View.GONE
        currencyListAdapter.setData(currencyList)
        this.currencyList = currencyList
    }


}