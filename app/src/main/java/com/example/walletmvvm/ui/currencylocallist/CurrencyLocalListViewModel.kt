package com.example.walletmvvm.ui.currencylocallist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.repositories.CurrencyLocalListRepository
import io.reactivex.Observable

class CurrencyLocalListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CurrencyLocalListRepository


    init {
        Log.v("appSenario", "CurrencyLocalListViewModel init")
        val currencyDao = WalletRoomDatabase.getDatabase(application).currencyDao()
        repository = CurrencyLocalListRepository.getInstance(currencyDao)

    }

    fun getCurrencyLists(): Observable<List<CurrencyModel>>? {

        return repository.getCurrencyList()
    }

}