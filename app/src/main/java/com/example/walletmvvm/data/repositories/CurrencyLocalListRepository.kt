package com.example.walletmvvm.data.repositories

import androidx.lifecycle.LiveData
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.model.CurrencyModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyLocalListRepository(private val currencyDao: CurrencyDao) {

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CurrencyLocalListRepository? = null

        fun getInstance(currencyDao: CurrencyDao) =
            instance ?: synchronized(this) {
                instance ?: CurrencyLocalListRepository(currencyDao).also { instance = it }
            }
    }


        fun getCurrencyList(): Observable<List<CurrencyModel>>? {

            return currencyDao.getCurrencyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        }




}
