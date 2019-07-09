package com.example.walletmvvm.ui.currencyserverlist

import com.example.walletmvvm.data.model.CurrencyModel
import io.reactivex.Observer

interface CurrencyServerListContract {

    interface View {

        var presenter: Presenter

        fun firstSetup()
        fun initUiListeners()
        fun showResult(result: String, showClickedButton: Boolean)
        fun visibleAddButton()
        fun setRecyclerData(currencyList: List<CurrencyModel>)
        fun getData(): Observer<List<CurrencyModel>>
    }

    interface Presenter {

        var currencyListServer: List<CurrencyModel>?
        fun getCurrencyListFromServer()

    }
}