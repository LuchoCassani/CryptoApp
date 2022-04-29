package com.raywenderlich.retrofitapirequest

import com.raywenderlich.retrofitapirequest.data.ArsData.CryptoDataArs
import com.raywenderlich.retrofitapirequest.data.dataUSD.CryptoDataUsd
import retrofit2.Response
import retrofit2.http.GET


interface CryptoAPI {

    //first call USD currency

    @GET ("v1/cryptocurrency/listings/latest?limit=10")
    suspend fun getCryptos(): Response<CryptoDataUsd>

    @GET("v1/cryptocurrency/listings/latest?limit=10&convert=ARS")
    suspend fun getCryptosArs():Response<CryptoDataArs>


}

//@Headers("X-CMC_PRO_API_KEY:d42376d6-7ab2-4667-8e48-a4b8094a8ceb",
//                    "Accept:application/json",
//    )