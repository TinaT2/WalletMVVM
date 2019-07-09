package com.example.walletmvvm.data.repositories

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.remote.APIClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyRepository(private val currencyDao: CurrencyDao) {

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CurrencyRepository? = null

        fun getInstance(currencyDao: CurrencyDao) =
            instance ?: synchronized(this) {
                instance ?: CurrencyRepository(currencyDao).also { instance = it }
            }
    }

    val currencyList: LiveData<List<CurrencyModel>> = currencyDao.getCurrencyList()

    fun insertCurrencyItemToDatabase(currencyModel: CurrencyModel) {
        InsertCurrencyAsyncTask(currencyDao).execute(currencyModel).get()
    }


    fun insertCurrencyListToDatabase(currencyListServer: List<CurrencyModel>) {
        for (currency in currencyListServer) {
            InsertCurrencyAsyncTask(currencyDao).execute(currency)
        }
    }

    @SuppressLint("CheckResult")
     fun requestCurrencyListFromServer():Observable<List<CurrencyModel>>? {

        Log.v("appSenario","requestCurrencyListFromServer in repository")


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
