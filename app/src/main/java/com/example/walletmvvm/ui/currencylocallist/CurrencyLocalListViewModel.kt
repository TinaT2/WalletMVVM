package com.example.walletmvvm.ui.currencylocallist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.repositories.CurrencyListRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyLocalListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CurrencyListRepository


    init {
        Log.v("appSenario", "CurrencyLocalListViewModel init")
        val currencyDao = WalletRoomDatabase.getDatabase(application).currencyDao()
        repository = CurrencyListRepository.getInstance(currencyDao)

    }

    fun getCurrencyLists(): Observable<List<CurrencyModel>>? {

        return repository.getCurrencyList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

}