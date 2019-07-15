package com.example.walletmvvm.ui.currencyserverlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.repositories.CurrencyListRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyServerListViewModel(application: Application) : AndroidViewModel(application) {

    private val listRepository: CurrencyListRepository

    init {
        Log.v("appSenario", "CurrencyServerListViewModel init")
        val currencyDao = WalletRoomDatabase.getDatabase(application).currencyDao()
        listRepository = CurrencyListRepository.getInstance(currencyDao)
    }

    fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {

        listRepository.insertCurrencyItemToDatabase(currencyModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun insertCurrencyListToDatabase(currencyList: List<CurrencyModel>) {
        //TODO live data
        listRepository.insertCurrencyListToDatabase(currencyList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun requestCurrencyListFromServer(): Observable<List<CurrencyModel>>? {
        //TODO send to livedata
        Log.v("appSenario", "requestCurrencyListFromServer in viewModel")
        return listRepository.requestCurrencyListFromServer()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

}