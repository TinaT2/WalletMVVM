package com.example.walletmvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.model.CurrencyModel

@Database(entities = [CurrencyModel::class], version = 1,exportSchema = false)
abstract class WalletRoomDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        private const val DATABASE_NAME = "wallet_database"

        const val NAME_CURRENCY_MODEL = "name"
        const val CODE_CURRENCY_MODEL = "code"
        const val SYMBOL_CURRENCY_MODEL = "symbol"
        const val TABLE_CURRENCY = "currency"

        @Volatile
        private var INSTANCE: WalletRoomDatabase? = null

        fun getDatabase(context: Context): WalletRoomDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WalletRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}