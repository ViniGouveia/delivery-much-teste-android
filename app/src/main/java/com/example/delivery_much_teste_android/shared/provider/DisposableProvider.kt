package com.example.delivery_much_teste_android.shared.provider

import io.reactivex.disposables.Disposable

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
interface DisposableProvider {
    fun add(disposable: Disposable)

    fun clear()

    fun dispose()
}
