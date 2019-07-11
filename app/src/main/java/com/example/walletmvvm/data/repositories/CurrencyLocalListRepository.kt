package com.example.walletmvvm.data.repositories

import androidx.lifecycle.LiveData
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.model.CurrencyModel

class CurrencyLocalListRepository(currencyDao: CurrencyDao) {

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CurrencyLocalListRepository? = null

        fun getInstance(currencyDao: CurrencyDao) =
            instance ?: synchronized(this) {
                instance ?: CurrencyLocalListRepository(currencyDao).also { instance = it }
            }
    }

    val currencyList: LiveData<List<CurrencyModel>> = currencyDao.getCurrencyList()

}
