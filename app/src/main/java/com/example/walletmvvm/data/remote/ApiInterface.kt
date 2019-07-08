package com.example.walletmvvm.data.remote

import com.example.walletmvvm.data.model.CurrencyModel
import retrofit2.Call
import retrofit2.http.HTTP

interface APIInterface {

    @HTTP(method = "LIST", path = "currencies", hasBody = true)
    fun currencyList(): Call<List<CurrencyModel>>


}