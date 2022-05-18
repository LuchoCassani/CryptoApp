package com.raywenderlich.retrofitapirequest


import com.raywenderlich.retrofitapirequest.data.CryptoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CryptoAPI {


    @GET("v1/cryptocurrency/listings/latest?limit=10")
    suspend fun getCryptos(): Response<CryptoData>

    @GET("v1/cryptocurrency/listings/latest?limit=10")
    suspend fun getCryptosArs(@Query("convert") convert: String): Response<CryptoData>


}


//buscar como pasar por parametro un valor a un endpoint en retrofit
