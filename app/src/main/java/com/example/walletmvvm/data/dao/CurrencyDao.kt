package com.example.walletmvvm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.model.CurrencyModel

import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface CurrencyDao {


    @Query("SELECT * FROM ${WalletRoomDatabase.TABLE_CURRENCY} ORDER BY ${WalletRoomDatabase.NAME_CURRENCY_MODEL} ASC")
    fun getCurrencyList(): Observable<List<CurrencyModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyModel: CurrencyModel): Completable
}