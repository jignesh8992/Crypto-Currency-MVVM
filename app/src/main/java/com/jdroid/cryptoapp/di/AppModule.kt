package com.jdroid.cryptoapp.di

import android.provider.SyncStateContract
import  com.jdroid.cryptoapp.BuildConfig
import com.jdroid.cryptoapp.common.BASE_URL
import com.jdroid.cryptoapp.data.remote.CoinApi
import com.jdroid.cryptoapp.data.repository.CoinRepositoryImpl
import com.jdroid.cryptoapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .build()
        okHttpClient
    } else {
        OkHttpClient().newBuilder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(provideBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient())
        .build()


    @Provides
    @Singleton
    fun provideCoinAPI(retrofit: Retrofit) = retrofit.create(CoinApi::class.java)


    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi): CoinRepository = CoinRepositoryImpl(api)


}