package com.raywenderlich.retrofitapirequest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val API: CryptoAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
            .create(CryptoAPI::class.java)
    }

    private fun getClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
        return client

    }


}


