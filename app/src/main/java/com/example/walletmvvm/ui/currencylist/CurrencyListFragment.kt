package com.example.walletmvvm.ui.currencylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walletmvvm.R
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.viewmodels.CurrencyViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_currencylist.*

class CurrencyListFragment : Fragment(), CurrencyListContract.View,
    CurrencyListAdapter.ViewCallbackInterface {


    override lateinit var presenter: CurrencyListContract.Presenter
    private lateinit var currencyListAdapter: CurrencyListAdapter

    private lateinit var currencyViewModel: CurrencyViewModel

    private var currencyList = emptyList<CurrencyModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currencylist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstSetup()
        initUiListeners()
    }

     override fun getData(): Observer<List<CurrencyModel>> {

         return object : Observer<List<CurrencyModel>> {


             override fun onNext(t: List<CurrencyModel>) {

                 setRecyclerData(t)
             }

             override fun onComplete() {

             }

             override fun onSubscribe(d: Disposable) {

             }

             override fun onError(e: Throwable) {

             }
         }
    }

    override fun firstSetup() {
        //
        currencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        //
        presenter = CurrencyListPresenter(this)
        //adapter
        recyclerview_currencylist_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        currencyListAdapter = CurrencyListAdapter(this)
        recyclerview_currencylist_list.adapter = currencyListAdapter

        presenter.getCurrencyListFromServer()

       // getCurrenciesFromDatabase()
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
//
//    private fun getCurrenciesFromDatabase(){
//        currencyViewModel.currencyList.observe(this, Observer { currencyList ->
//            // Update the cached copy of the words in the adapter.
//            currencyList?.let {
//                for (currency in it){
//                    Log.v("currencyList",currency.name?:" ")
//                }}
//        })
//    }
}