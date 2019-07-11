package com.example.walletmvvm.data.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.repositories.CurrencyRepository
import io.reactivex.Observable

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CurrencyRepository


    init {
        Log.v("appSenario","CurrencyViewModel init")
        val currencyDao = WalletRoomDatabase.getDatabase(application).currencyDao()
        repository = CurrencyRepository.getInstance(currencyDao)

    }

     fun getCurrencyLists(): Observable<List<CurrencyModel>>? {

        return repository.getCurrencyList()
    }

    fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {
        repository.insertCurrencyItemToDatabase(currencyModel)
    }

    fun insertCurrencyListToDatabase(currencyList:List<CurrencyModel>){
        repository.insertCurrencyListToDatabase(currencyList)
    }

    fun requestCurrencyListFromServer(): Observable<List<CurrencyModel>>?{
        Log.v("appSenario","requestCurrencyListFromServer in viewModel")
        return repository.requestCurrencyListFromServer()
    }
}