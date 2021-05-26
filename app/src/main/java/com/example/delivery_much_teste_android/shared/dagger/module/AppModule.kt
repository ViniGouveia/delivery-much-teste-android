package com.example.delivery_much_teste_android.shared.dagger.module

import android.content.Context
import com.example.delivery_much_teste_android.shared.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Module
@Suppress("unused")
object AppModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideContext(application: BaseApplication): Context = application.applicationContext

}
