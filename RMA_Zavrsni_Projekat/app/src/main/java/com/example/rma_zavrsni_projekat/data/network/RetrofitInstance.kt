package com.example.rma_zavrsni_projekat.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {

    private const val BASE_URL = "https://odp.iddeea.gov.ba:8096/"
    private const val API_TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMDU5IiwibmJmIjoxNzUxMzY2Mjg2LCJleHAiOjE3NTE0NTI2ODYsImlhdCI6MTc1MTM2NjI4Nn0.xE2BCQkHcHgc40bCI-C8Z2QRdlk5KrFnvPKw0JxwEeb99gpMr4K0RYVWgeTnQwqJ2MbFMsaXJOH-XaxkK_RYfg"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor{ chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $API_TOKEN")
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
        .build()

    val api: OpenDataApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(OpenDataApi::class.java)
    }
}