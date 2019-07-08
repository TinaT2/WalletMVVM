package com.example.walletmvvm.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {

    private const val BASE_URL = "https://nightly-alpha.carrene.com/apiv1/"

    private fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(): APIInterface? =
        getClient().create(APIInterface::class.java)

}