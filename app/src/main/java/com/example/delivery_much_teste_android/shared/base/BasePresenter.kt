package com.example.delivery_much_teste_android.shared.base

import com.example.delivery_much_teste_android.shared.network.ThrowableHandler
import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
abstract class BasePresenter<ViewType : BaseContract.ActionView>(
    protected val view: ViewType,
    protected val disposableProvider: DisposableProvider? = null,
    protected val schedulerProvider: SchedulerProvider? = null,
    private val throwableHandler: ThrowableHandler? = null
) : BaseContract.Presenter {

    override fun interrupt() {
        disposableProvider?.clear()
    }

    override fun detach() {
        disposableProvider?.dispose()
    }

    protected fun showError(throwable: Throwable) {
        val messageException = getThrowableMessage(throwable)
        val message = messageException.message ?: view.getString(messageException.messageResId!!)
        view.showError(message)
    }

    private fun getThrowableMessage(throwable: Throwable) =
        throwableHandler?.getExceptionMessage(throwable)!!

    protected fun <T> observeOnUiAfterResult() = UIThreadResultTransformer<T>()

    protected fun observeOnUiAfterCompletion() = UIThreadCompletionTransformer()

    inner class UIThreadCompletionTransformer : CompletableTransformer {
        override fun apply(upstream: Completable): CompletableSource =
            upstream
                .subscribeOn(schedulerProvider?.io())
                .observeOn(schedulerProvider?.ui())
    }

    inner class UIThreadResultTransformer<T> : SingleTransformer<T, T> {
        override fun apply(upstream: Single<T>): SingleSource<T> =
            upstream
                .subscribeOn(schedulerProvider?.io())
                .observeOn(schedulerProvider?.ui())
    }
}
