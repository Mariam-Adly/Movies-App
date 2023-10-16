package com.example.moviesapplication.network

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYzdlMzhjNzkwMmQ1MzgyODliY2M2MTQ3Yzc2NjJkYiIsInN1YiI6IjY1MmMzZmQzZjI4ODM4MDJhMjVlYjk0MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.b1EFMgxP2bzXkrwdtE5H93WyL47mmbJnc_z3Ez3NzOk")
            .build()
        return chain.proceed(request)
    }
}