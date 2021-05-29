package com.example.delivery_much_teste_android.shared.dagger.module

import android.content.Context
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.network.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Module
object NetworkModule {

    private const val DEFAULT_TIMEOUT = 60L

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(
        context: Context
    ): Retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.api_url))
        .client(getHeader())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun getHeader(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())

        return builder.build()
    }
}