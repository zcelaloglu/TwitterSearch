package com.celaloglu.zafer.twitter.di.module

import com.celaloglu.zafer.twitter.BuildConfig
import com.celaloglu.zafer.twitter.api.service.TwitterService
import com.celaloglu.zafer.twitter.di.scopes.PerApplication
import com.celaloglu.zafer.twitter.util.DateDeserializer
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @PerApplication
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @PerApplication
    internal fun provideTwitterService(okHttpClient: OkHttpClient): TwitterService {
        val httpClientBuilder = okHttpClient.newBuilder()

        val gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClientBuilder.build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(TwitterService::class.java)
    }
}