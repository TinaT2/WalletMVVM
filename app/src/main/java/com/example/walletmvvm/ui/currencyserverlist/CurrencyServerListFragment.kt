package com.example.walletmvvm.ui.currencyserverlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walletmvvm.R
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.viewmodels.CurrencyViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_currencyserverlist.*

class CurrencyServerListFragment : Fragment(), CurrencyServerListContract.View,
    CurrencyServerListAdapter.ViewCallbackInterface {


    override lateinit var presenter: CurrencyServerListContract.Presenter
    private lateinit var currencyListAdapter: CurrencyServerListAdapter

    private lateinit var currencyViewModel: CurrencyViewModel

    private var currencyList = emptyList<CurrencyModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currencyserverlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()
    }

    override fun firstSetup() {
        //
        currencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        //
        presenter = CurrencyServerListPresenter(this)
        //adapter
        recyclerview_currencylist_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        currencyListAdapter = CurrencyServerListAdapter(this)
        recyclerview_currencylist_list.adapter = currencyListAdapter

        presenter.getCurrencyListFromServer()
    }

     override fun setRecyclerData(currencyList:List<CurrencyModel>) {
        progressbar_currencylist_progress?.visibility = View.GONE
         currencyListAdapter.setData(currencyList)
         this.currencyList = currencyList
    }


    override fun initUiListeners() {
    }

    override fun showResult(result: String, showClickedButton: Boolean) {
        constraintlayout_currencylist_base?.let { Snackbar.make(it, result, Snackbar.LENGTH_LONG).show() }
    }

    override fun visibleAddButton() {
        val addAllButton =
            activity?.findViewById<androidx.appcompat.widget.AppCompatImageButton>(R.id.imagebutton_currencylist_addall)
        addAllButton?.visibility = View.VISIBLE
        addAllButton?.setOnClickListener {
            insertCurrencyListToDatabase(currencyList)
        }
    }

     override fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {
        currencyViewModel.insertCurrencyItemToDatabase(currencyModel)
    }

     private fun insertCurrencyListToDatabase(currencyList: List<CurrencyModel>) {
        currencyViewModel.insertCurrencyListToDatabase(currencyList)
    }

    override fun showResultCallback(message: String) {
        showResult(message, false)
    }



}