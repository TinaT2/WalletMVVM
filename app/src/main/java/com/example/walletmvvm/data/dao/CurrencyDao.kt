package com.example.walletmvvm.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM ${WalletRoomDatabase.TABLE_CURRENCY} ORDER BY ${WalletRoomDatabase.NAME_CURRENCY_MODEL} ASC")
    fun getCurrencyList(): LiveData<List<CurrencyModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyModel: CurrencyModel): Long
}