package com.example.walletmvvm.di

import com.example.walletmvvm.data.database.WalletRoomDatabase
import com.example.walletmvvm.data.repositories.CurrencyListRepository
import com.example.walletmvvm.ui.currencyserverlist.CurrencyServerListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val currencyListServerModule = module {

    viewModel { CurrencyServerListViewModel(get()) }
    single { WalletRoomDatabase.getDatabase(androidApplication()).currencyDao() }
    single { CurrencyListRepository.getInstance(get()) }
}