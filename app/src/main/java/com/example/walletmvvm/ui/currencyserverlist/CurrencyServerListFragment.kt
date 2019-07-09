package com.example.walletmvvm.ui.currencyserverlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walletmvvm.R
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.viewmodels.CurrencyViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_currencyserverlist.*

class CurrencyServerListFragment : Fragment(),
    CurrencyServerListAdapter.ViewCallbackInterface {

    private lateinit var currencyListAdapter: CurrencyServerListAdapter

    private lateinit var currencyViewModel: CurrencyViewModel

    private var currencyList = emptyList<CurrencyModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("appSenario","CurrencyServerListFragment create")
        return inflater.inflate(R.layout.fragment_currencyserverlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initUIComponents()
    }


    private fun initUIComponents() {
        initViewModel()
        initAdapter()
        initObservers()
    }

    fun setRecyclerData(currencyList: List<CurrencyModel>) {
        invisibleProgressBar()
        currencyListAdapter.setData(currencyList)
        this.currencyList = currencyList
    }


    private fun responseCurrencyListFromServer(): Observer<List<CurrencyModel>> {

        return object : Observer<List<CurrencyModel>> {

            override fun onNext(currencyList: List<CurrencyModel>) {
                Log.v("appSenario", "responseCurrencyListFromServer")
                setRecyclerData(currencyList)
                visibleAddAllButton()
            }

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
            }
        }
    }

    private fun initViewModel() {
        currencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
    }

    private fun initAdapter() {
        recyclerview_currencylist_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        currencyListAdapter = CurrencyServerListAdapter(this)
        recyclerview_currencylist_list.adapter = currencyListAdapter
    }

    @SuppressLint("CheckResult")
    private fun initObservers() {
        val requestCurrencyListObserver = currencyViewModel.requestCurrencyListFromServer()
        requestCurrencyListObserver?.subscribeWith(responseCurrencyListFromServer())
    }

    private fun invisibleProgressBar() {
        progressbar_currencylist_progress?.visibility = View.GONE
    }

    private fun showResult(result: String) {
        constraintlayout_currencylist_base?.let { Snackbar.make(it, result, Snackbar.LENGTH_LONG).show() }
    }

    private fun visibleAddAllButton() {
        val addAllButton =
            activity?.findViewById<androidx.appcompat.widget.AppCompatImageButton>(R.id.imagebutton_currencylist_addall)
        addAllButton?.visibility = View.VISIBLE
        addAllButton?.setOnClickListener {
            insertCurrencyListToDatabase(currencyList)
        }
    }

    private fun insertCurrencyListToDatabase(currencyList: List<CurrencyModel>) {
        currencyViewModel.insertCurrencyListToDatabase(currencyList)
    }

    override fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {
        currencyViewModel.insertCurrencyItemToDatabase(currencyModel)
    }

    override fun showResultCallback(message: String) {
        showResult(message)
    }

    override fun onResume() {
        super.onResume()
        Log.v("appSenario","CurrencyServerListFragment onResume")

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.v("appSenario","CurrencyServerListFragment onCreate")

    }

}