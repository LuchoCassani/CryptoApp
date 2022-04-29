package com.raywenderlich.retrofitapirequest

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-CMC_PRO_API_KEY","d42376d6-7ab2-4667-8e48-a4b8094a8ceb")
            //.addHeader("Accept:","application/json")
            .build()
        return chain.proceed(request)

    }

}