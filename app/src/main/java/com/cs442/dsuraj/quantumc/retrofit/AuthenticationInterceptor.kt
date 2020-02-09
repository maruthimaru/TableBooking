package com.scoto.visitormanagent.retrofit

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor(private val authToken: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val orignal = chain.request()

        val builder = orignal.newBuilder()
                .header("Authorization", authToken)
        val request = builder.build()

        return chain.proceed(request)
    }
}
