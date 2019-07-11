package com.example.walletmvvm.ui.currencylocallist

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
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.viewmodels.CurrencyViewModel
import com.example.walletmvvm.databinding.FragmentCurrencylocallistBinding
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_currencylocallist.*


class CurrencyLocalListFragment : Fragment() {

    private lateinit var currencyListAdapter: CurrencyLocalListAdapter

    private lateinit var currencyViewModel: CurrencyViewModel

    private var currencyList = emptyList<CurrencyModel>()

    private lateinit var binding: FragmentCurrencylocallistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.v("appSenario", "CurrencyLocalListFragment create")
        binding = FragmentCurrencylocallistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
    }

    private fun firstSetup() {

        currencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)

        recyclerview_currencylist_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        currencyListAdapter = CurrencyLocalListAdapter(currencyList)
        recyclerview_currencylist_list.adapter = currencyListAdapter

        initObservers()
        getCurrenciesFromDatabase()
    }


    @SuppressLint("CheckResult")
    private fun initObservers() {
        val getCurrencyListObserver = currencyViewModel.getCurrencyLists()
        getCurrencyListObserver?.subscribe(getCurrenciesFromDatabase())
    }

    private fun showResult(result: String) {
        constraintlayout_currencylist_base?.let { Snackbar.make(it, result, Snackbar.LENGTH_LONG).show() }
    }

    private fun getCurrenciesFromDatabase(): Observer<List<CurrencyModel>> {


        return object : Observer<List<CurrencyModel>> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<CurrencyModel>) {

                t.let {

                    showResult(it.size.toString() + " items")
                    setRecyclerData(it)
                    binding.listSize = it.size
                }
            }

            override fun onError(e: Throwable) {

            }
        }
    }


    private fun setRecyclerData(currencyList: List<CurrencyModel>) {
        progressbar_currencylist_progress?.visibility = View.GONE
        currencyListAdapter.setData(currencyList)
        this.currencyList = currencyList
    }

    override fun onResume() {
        super.onResume()
        Log.v("appSenario", "CurrencyLocalListFragment onResume")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("appSenario", "CurrencyLocalListFragment onCreate")

    }

}