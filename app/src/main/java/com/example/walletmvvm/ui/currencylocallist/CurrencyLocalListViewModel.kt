package com.example.walletmvvm.ui.currencylocallist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.repositories.CurrencyLocalListRepository

class CurrencyLocalListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CurrencyLocalListRepository
    val currencyList: LiveData<List<CurrencyModel>>

    init {
        Log.v("appSenario", "CurrencyLocalListViewModel init")
        val currencyDao = WalletRoomDatabase.getDatabase(application).currencyDao()
        repository = CurrencyLocalListRepository.getInstance(currencyDao)
        currencyList = repository.currencyList
    }

}