package com.example.baloot.alimehdizadeh.data.source.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Content-Type", "application/json")
        return chain.proceed(builder.build())
    }
}