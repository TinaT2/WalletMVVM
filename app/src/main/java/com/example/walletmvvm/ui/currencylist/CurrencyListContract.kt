package com.example.walletmvvm.ui.currencylist

import com.example.walletmvvm.data.model.CurrencyModel

interface CurrencyListContract {

    interface View {

        var presenter: Presenter

        fun firstSetup()
        fun initUiListeners()
        fun showResult(result: String, showClickedButton: Boolean)
        fun visibleAddButton()
        fun setRecyclerData(currencyList: List<CurrencyModel>)
    }

    interface Presenter {

        var currencyListServer: List<CurrencyModel>?
        fun getCurrencyListFromServer()

    }
}