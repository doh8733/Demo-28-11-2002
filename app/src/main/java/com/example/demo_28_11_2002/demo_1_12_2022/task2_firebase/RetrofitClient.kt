package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase

import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://fcm.googleapis.com/fcm/"
    private val moshi = Moshi.Builder() // adapter
        .add(KotlinJsonAdapterFactory())
        .build()

    private var retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())

    private var build = retrofit.build()
    var api: TodoApi = build.create(TodoApi::class.java)

    //
    private const val INSTAGRAM_URL ="https://api.instagram.com/v1/"
    private val retrofit2 =Retrofit.Builder()
        .baseUrl(INSTAGRAM_URL)
        .addConverterFactory(GsonConverterFactory.create())

     val instagramApi = retrofit2.build()

}