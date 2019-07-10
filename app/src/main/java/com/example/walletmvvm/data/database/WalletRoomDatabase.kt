package com.example.walletmvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.walletmvvm.data.dao.CurrencyDao
import com.example.walletmvvm.data.model.CurrencyModel
import com.example.walletmvvm.utils.DbConstants

@Database(entities = [CurrencyModel::class], version = 1,exportSchema = false)
abstract class WalletRoomDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
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
                    DbConstants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}