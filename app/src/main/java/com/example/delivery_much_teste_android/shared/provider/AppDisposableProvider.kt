package com.example.delivery_much_teste_android.shared.provider

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
class AppDisposableProvider : DisposableProvider {

    private val compositeDisposable = CompositeDisposable()

    override fun clear() {
        compositeDisposable.clear()
    }

    override fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}
