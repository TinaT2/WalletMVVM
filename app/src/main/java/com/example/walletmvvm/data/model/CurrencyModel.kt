package com.example.walletmvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.google.gson.annotations.SerializedName

@Entity(tableName = WalletRoomDatabase.TABLE_CURRENCY)
data class CurrencyModel
    (
    @SerializedName(WalletRoomDatabase.NAME_CURRENCY_MODEL) @ColumnInfo(name = WalletRoomDatabase.NAME_CURRENCY_MODEL) val name: String?,
    @SerializedName(WalletRoomDatabase.CODE_CURRENCY_MODEL) @PrimaryKey @ColumnInfo(name = WalletRoomDatabase.CODE_CURRENCY_MODEL) val code: String,
    @SerializedName(WalletRoomDatabase.SYMBOL_CURRENCY_MODEL) @ColumnInfo(name = WalletRoomDatabase.SYMBOL_CURRENCY_MODEL) val symbol: String?
)