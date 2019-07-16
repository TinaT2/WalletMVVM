package com.example.walletmvvm.ui.currencyserverlist

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
import com.example.walletmvvm.data.repositories.CurrencyListRepository
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_currencyserverlist.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CurrencyServerListFragment : Fragment(),
    CurrencyServerListAdapter.ViewCallbackInterface {

    private lateinit var currencyListAdapter: CurrencyServerListAdapter

    private  val currencyViewModel: CurrencyServerListViewModel by viewModel()

    private var currencyList = emptyList<CurrencyModel>()



    private val mRecyclerView by lazy { recyclerview_currencylist_list }
    private val mProgressBar by lazy { progressbar_currencylist_progress }
    private val mRootView by lazy { constraintlayout_currencylist_base }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.v("appSenario", "CurrencyServerListFragment create")
        return inflater.inflate(R.layout.fragment_currencyserverlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUIComponents()
    }


    private fun initUIComponents() {
        initAdapter()
        initObservers()
    }

    private fun setRecyclerData(currencyList: List<CurrencyModel>) {
        invisibleProgressBar()
        currencyListAdapter.setData(currencyList)
        this.currencyList = currencyList
    }

    private fun initAdapter() {
        currencyListAdapter = CurrencyServerListAdapter(this)
        mRecyclerView?.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mRecyclerView?.adapter = currencyListAdapter
    }


    private fun initObservers() {
        currencyViewModel.requestCurrencyListFromServer()
        currencyViewModel.requestCurrencyListFromServerLiveData.observe(this,
            androidx.lifecycle.Observer{currencyList->
            Log.v("appSenario", "responseCurrencyListFromServer")
            currencyList?.let{
                setRecyclerData(it)
                visibleAddAllButton() }
        })
    }

    private fun invisibleProgressBar() {
        mProgressBar?.visibility = View.GONE
    }

    private fun showResult(result: String) {
        mRootView?.let { Snackbar.make(it, result, Snackbar.LENGTH_LONG).show() }
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
        Log.v("appSenario", "CurrencyServerListFragment onResume")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("appSenario", "CurrencyServerListFragment onCreate")

    }
}