package com.example.walletmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.repositories.CurrencyRepository

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CurrencyRepository
    val currencyList: LiveData<List<CurrencyModel>>

    init {
        val currencyDao = WalletRoomDatabase.getDatabase(application).currencyDao()
        repository = CurrencyRepository.getInstance(currencyDao)
        currencyList = repository.currencyList
    }

    fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {
        repository.insertCurrencyItemToDatabase(currencyModel)
    }

    fun insertCurrencyListToDatabase(currencyList:List<CurrencyModel>){
        repository.insertCurrencyListToDatabase(currencyList)
    }

}