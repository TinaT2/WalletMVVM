package com.example.walletmvvm.data.repositories

import android.util.Log
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.remote.APIClient
import io.reactivex.Observable
import io.reactivex.Single


class CurrencyListRepository(private val currencyDao: CurrencyDao) {


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CurrencyListRepository? = null

        fun getInstance(currencyDao: CurrencyDao) =
            instance ?: synchronized(this) {
                instance ?: CurrencyListRepository(currencyDao)
                    .also { instance = it }
            }
    }


    fun getCurrencyList(): Observable<List<CurrencyModel>>? {

        return currencyDao.getCurrencyList()

    }

    fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel): Single<Long> {
        return currencyDao.insert(currencyModel)
    }

    fun insertCurrencyListToDatabase(currencyListServer: List<CurrencyModel>): Single<Long> {
        val list = currencyListServer.toTypedArray()
        return currencyDao.insert(*list)
    }


    fun requestCurrencyListFromServer(): Observable<List<CurrencyModel>>? {

        Log.v("appSenario", "requestCurrencyListFromServer in repository")

        val interfaceApi = APIClient.getService()
        return interfaceApi?.currencyList()

    }
}

