package com.example.delivery_much_teste_android.shared.dagger.module

import com.example.delivery_much_teste_android.shared.service.RepositoryService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
@Module
object ServiceModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideRepositoryService(retrofit: Retrofit): RepositoryService =
        retrofit.create(RepositoryService::class.java)
}