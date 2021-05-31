package com.example.delivery_much_teste_android.shared.extensions

import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import io.reactivex.disposables.Disposable

/**
 * @author Vinicius Gouveia on 29/05/2021
 */

fun Disposable.addTo(disposableProvider: DisposableProvider) =
    apply { disposableProvider.add(this) }
