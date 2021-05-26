package com.example.delivery_much_teste_android.shared.dagger.module

import com.example.delivery_much_teste_android.ui.MainActivity
import com.example.delivery_much_teste_android.ui.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Suppress("TooManyFunctions")
@Module
interface BuildersModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun bindMainActivity(): MainActivity
}