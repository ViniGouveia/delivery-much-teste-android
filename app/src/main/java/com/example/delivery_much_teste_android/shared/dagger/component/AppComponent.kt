package com.example.delivery_much_teste_android.shared.dagger.component

import com.example.delivery_much_teste_android.shared.BaseApplication
import com.example.delivery_much_teste_android.shared.dagger.module.AppModule
import com.example.delivery_much_teste_android.shared.dagger.module.BuildersModule
import com.example.delivery_much_teste_android.shared.dagger.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        AppModule::class,
        BuildersModule::class
    ]
)

@Suppress("unused")
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(baseApplication: BaseApplication): Builder

        fun build(): AppComponent
    }
}
