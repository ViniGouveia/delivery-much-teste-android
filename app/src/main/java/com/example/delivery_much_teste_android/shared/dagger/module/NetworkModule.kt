package com.example.delivery_much_teste_android.shared.dagger.module

import android.content.Context
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.network.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Module
object NetworkModule {

    private const val TIMEOUT_TIME = 60L
    private const val PROTOCOL = "SSL"
    private const val FIRST_TRUST_MANAGER = 0

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(
        context: Context
    ): Retrofit =
        OkHttpClient.Builder()
            .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
            .sslSocketFactory(getSSLSocketFactory(getTrustManager()), getTrustManager())
            .build().let { client ->
                Retrofit.Builder()
                    .baseUrl(context.getString(R.string.base_api_url))
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }

    private fun getTrustManager(): X509TrustManager {
        val trustManagers: Array<TrustManager> = arrayOf(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) = Unit

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) = Unit

                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            }
        )
        return trustManagers[FIRST_TRUST_MANAGER] as X509TrustManager
    }

    private fun getSSLSocketFactory(trustManager: X509TrustManager): SSLSocketFactory {
        val sslContext: SSLContext = SSLContext.getInstance(PROTOCOL)
        sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
        return sslContext.socketFactory
    }
}