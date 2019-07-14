package com.example.walletmvvm.ui.currencyserverlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.repositories.CurrencyServerListRepository
import io.reactivex.Observable

class CurrencyServerListViewModel(application: Application) : AndroidViewModel(application) {

    private val serverListRepository: CurrencyServerListRepository

    init {
        Log.v("appSenario", "CurrencyServerListViewModel init")
        val currencyDao = WalletRoomDatabase.getDatabase(application).currencyDao()
        serverListRepository = CurrencyServerListRepository.getInstance(currencyDao)
    }

    fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {
        serverListRepository.insertCurrencyItemToDatabase(currencyModel)
    }

    fun insertCurrencyListToDatabase(currencyList: List<CurrencyModel>) {
        serverListRepository.insertCurrencyListToDatabase(currencyList)
    }

    fun requestCurrencyListFromServer(): Observable<List<CurrencyModel>>? {
        Log.v("appSenario", "requestCurrencyListFromServer in viewModel")
        return serverListRepository.requestCurrencyListFromServer()
    }

}