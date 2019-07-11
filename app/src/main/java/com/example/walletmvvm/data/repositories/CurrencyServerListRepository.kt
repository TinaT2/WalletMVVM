package com.example.walletmvvm.data.repositories

import android.annotation.SuppressLint
import android.util.Log
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.remote.APIClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CurrencyServerListRepository(private val currencyDao: CurrencyDao) {


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CurrencyServerListRepository? = null

        fun getInstance(currencyDao: CurrencyDao) =
            instance ?: synchronized(this) {
                instance ?: CurrencyServerListRepository(currencyDao).also { instance = it }
            }
    }

    fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {

        currencyDao.insert(currencyModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun insertCurrencyListToDatabase(currencyListServer: List<CurrencyModel>) {

        for (currency in currencyListServer) {

            currencyDao.insert(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    @SuppressLint("CheckResult")
    fun requestCurrencyListFromServer(): Observable<List<CurrencyModel>>? {

        Log.v("appSenario", "requestCurrencyListFromServer in repository")

        val interfaceApi = APIClient.getService()
        return interfaceApi?.currencyList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }
}

