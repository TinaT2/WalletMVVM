package com.example.walletmvvm.data.repositories

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.data.remote.APIClient
import com.example.walletmvvm.data.remote.APIInterface
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


     fun insertCurrencyListToDatabase(currencyListServer:List<CurrencyModel>) {
        for (currency in currencyListServer) {
            InsertCurrencyAsyncTask(currencyDao).execute(currency)
        }
    }

}
    //Async Tasks___________________________________
    private class InsertCurrencyAsyncTask(val currencyDao: CurrencyDao) :

        AsyncTask<CurrencyModel, Void, Long>() {
        override fun doInBackground(vararg currencyModel: CurrencyModel?): Long {

            return currencyModel[0]?.let { currencyDao.insert(it) } ?: 0L
        }
    }
