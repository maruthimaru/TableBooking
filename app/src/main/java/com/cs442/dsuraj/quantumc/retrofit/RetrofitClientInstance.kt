package com.scoto.visitormanagent.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClientInstance {

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private var retrofit: Retrofit? = null
    private val httpClient = OkHttpClient.Builder()
    private val TAG = RetrofitClientInstance::class.java.simpleName

    fun createServices(serviceClass: Class<ApiService>,baseUrl:String): ApiService {
        Log.e(TAG, "createServices: ")
       val url=baseUrl
        val builder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        }
        val authToken = Credentials.basic("admin", "1234")
     //  Log.e(TAG, "createServices:authToken : $authToken")
        val interceptor = AuthenticationInterceptor(authToken)
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor)
            builder.client(httpClient.build())
            retrofit = builder.build()
        }
        return retrofit!!.create(serviceClass)
    }
}
