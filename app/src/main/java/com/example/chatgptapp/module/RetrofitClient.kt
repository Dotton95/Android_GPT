package com.example.chatgptapp.module

import com.example.chatgptapp.BuildConfig.API_KEY
import com.example.chatgptapp.BuildConfig.API_URL

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private fun initRetrofit(factory:Converter.Factory): Retrofit{

        val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val module = OkHttpClient.Builder()
            .readTimeout(20000,TimeUnit.MILLISECONDS)
            .connectTimeout(20000,TimeUnit.MILLISECONDS)
            .addInterceptor(logInterceptor)

        module.addInterceptor(AuthInterceptor())

        val client = module.build()

        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
    }
    class AuthInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder: Request.Builder = chain.request().newBuilder()
            builder.addHeader("Authorization","Bearer $API_KEY")
            return chain.proceed(builder.build())
        }
    }
    fun init():GptAPI = initRetrofit(GsonConverterFactory.create())!!.create(GptAPI::class.java)

}