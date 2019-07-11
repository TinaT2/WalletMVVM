package com.example.walletmvvm.data.repositories

import android.annotation.SuppressLint
import android.os.AsyncTask
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
        InsertCurrencyAsyncTask(currencyDao).execute(currencyModel).get()
    }


    fun insertCurrencyListToDatabase(currencyListServer: List<CurrencyModel>) {
        for (currency in currencyListServer) {
            InsertCurrencyAsyncTask(currencyDao).execute(currency)
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

//Async Tasks___________________________________
private class InsertCurrencyAsyncTask(val currencyDao: CurrencyDao) :

    AsyncTask<CurrencyModel, Void, Long>() {
    override fun doInBackground(vararg currencyModel: CurrencyModel?): Long {

        return currencyModel[0]?.let { currencyDao.insert(it) } ?: 0L
    }
}
