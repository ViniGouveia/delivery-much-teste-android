package com.example.delivery_much_teste_android.shared

import androidx.appcompat.app.AppCompatDelegate
import com.example.delivery_much_teste_android.shared.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
