package com.example.walletmvvm

import android.app.Application
import com.example.walletmvvm.di.currencyListServerModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WalletMvvmApplication : Application() {

    companion object {
        lateinit var instance: WalletMvvmApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Stetho.initializeWithDefaults(this)

        startKoin {
            modules(currencyListServerModule)
            androidContext(this@WalletMvvmApplication)
        }
    }
}