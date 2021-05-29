package com.example.delivery_much_teste_android.shared.dagger.module

import android.content.Context
import com.example.delivery_much_teste_android.shared.BaseApplication
import com.example.delivery_much_teste_android.shared.network.AppThrowableHandler
import com.example.delivery_much_teste_android.shared.network.ThrowableHandler
import com.example.delivery_much_teste_android.shared.provider.AppDisposableProvider
import com.example.delivery_much_teste_android.shared.provider.AppSchedulerProvider
import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Module
object AppModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideContext(application: BaseApplication): Context = application.applicationContext

    @Provides
    @Reusable
    @JvmStatic
    fun provideDisposableProvider(): DisposableProvider = AppDisposableProvider()

    @Provides
    @Reusable
    @JvmStatic
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    @Reusable
    @JvmStatic
    fun provideThrowableHandler(
        retrofit: Retrofit,
        context: Context
    ): ThrowableHandler = AppThrowableHandler(
        retrofit,
        context
    )
}
