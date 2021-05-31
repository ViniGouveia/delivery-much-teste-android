package com.example.delivery_much_teste_android.shared.network

import android.os.Build
import com.example.delivery_much_teste_android.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request().newBuilder().build()

        original = original.newBuilder().apply {
            addHeader(OS_KEY, OS_NAME)
            addHeader(APP_VERSION_KEY, BuildConfig.VERSION_NAME)
            addHeader(USER_AGENT_AUTHORIZATION, BuildConfig.APPLICATION_ID)
            addHeader(OS_VERSION_KEY, Build.VERSION.SDK_INT.toString())
            addHeader(HEADER_KEY_AUTHORIZATION, KEY_AUTHORIZATION)

        }.build()

        return chain.proceed(original)
    }

    companion object {
        private const val KEY_AUTHORIZATION = "application/vnd.github.v3+json"
        private const val APP_VERSION_KEY = "AppVersion"
        private const val USER_AGENT_AUTHORIZATION = "User-Agent"
        private const val OS_KEY = "Os"
        private const val OS_VERSION_KEY = "OsVersion"
        private const val OS_NAME = "Android"
        private const val HEADER_KEY_AUTHORIZATION = "Accept"
    }
}
