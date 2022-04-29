package com.raywenderlich.retrofitapirequest

import com.raywenderlich.retrofitapirequest.data.dataUSD.cryptoDataUsd
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface CryptoAPI {

    //first call USD currency

    @GET ("v1/cryptocurrency/listings/latest?limit=10")
    suspend fun getCryptos(): Response<cryptoDataUsd>

    @GET("v1/cryptocurrency/listings/latest?limit=10&convert=ARS")
    suspend fun getCryptosArs():Response<>


}

//@Headers("X-CMC_PRO_API_KEY:d42376d6-7ab2-4667-8e48-a4b8094a8ceb",
//                    "Accept:application/json",
//    )