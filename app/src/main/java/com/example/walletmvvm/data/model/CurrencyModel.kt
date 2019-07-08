package com.example.walletmvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.walletmvvm.utils.DbConstants
import com.google.gson.annotations.SerializedName

@Entity(tableName = DbConstants.TABLE_CURRENCY)
data class CurrencyModel
    (
    @SerializedName(DbConstants.NAME_CURRENCY_MODEL) @ColumnInfo(name = DbConstants.NAME_CURRENCY_MODEL) val name: String?,
    @SerializedName(DbConstants.CODE_CURRENCY_MODEL) @PrimaryKey @ColumnInfo(name = DbConstants.CODE_CURRENCY_MODEL) val code: String,
    @SerializedName(DbConstants.SYMBOL_CURRENCY_MODEL) @ColumnInfo(name = DbConstants.SYMBOL_CURRENCY_MODEL) val symbol: String?
)