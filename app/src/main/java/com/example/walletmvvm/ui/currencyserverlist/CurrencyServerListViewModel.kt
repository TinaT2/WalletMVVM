package com.example.walletmvvm.ui.currencyserverlist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.repositories.CurrencyListRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyServerListViewModel(private val currencyListRepository:CurrencyListRepository) : ViewModel() {
 val insertCurrencyItemToDatabaseLiveData  = MutableLiveData<Long>()
 val insertCurrencyListToDatabaseLiveData  = MutableLiveData<List<Long>>()
 val requestCurrencyListFromServerLiveData  = MutableLiveData<List<CurrencyModel>>()

    @SuppressLint("CheckResult")
    fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {
        currencyListRepository.insertCurrencyItemToDatabase(currencyModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({insertCurrencyItemToDatabaseLiveData.value=it},{})
    }

    @SuppressLint("CheckResult")
    fun insertCurrencyListToDatabase(currencyList: List<CurrencyModel>) {
        //TODO live data
        currencyListRepository.insertCurrencyListToDatabase(currencyList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({insertCurrencyListToDatabaseLiveData.value = it},{})
    }

    @SuppressLint("CheckResult")
    fun requestCurrencyListFromServer(){
        //TODO send to livedata
        Log.v("appSenario", "requestCurrencyListFromServer in viewModel")
         currencyListRepository.requestCurrencyListFromServer()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({requestCurrencyListFromServerLiveData.value = it},{})
    }

}