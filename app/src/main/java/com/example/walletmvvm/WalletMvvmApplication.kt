package com.example.walletmvvm

import android.app.Application
import com.facebook.stetho.Stetho

class WalletMvvmApplication:Application() {

    companion object {
        lateinit var instance: WalletMvvmApplication
        //private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Stetho.initializeWithDefaults(this)
    }
}