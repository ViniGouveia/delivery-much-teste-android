package com.example.delivery_much_teste_android.ui

import dagger.Module
import dagger.Provides

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Module
class MainModule {

    @Provides
    fun providePresenter(
        view: MainActivity
    ): MainContract.Presenter = MainPresenterImpl(view)
}