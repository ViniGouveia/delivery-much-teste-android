package com.example.delivery_much_teste_android.shared.base

import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.network.ThrowableHandler
import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider
import io.reactivex.*

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

    protected fun getErrorMessage(throwable: Throwable): String {
        val messageException = getThrowableMessage(throwable)
        return messageException.message ?: view.getString(messageException.messageResId!!)
    }

    protected fun showError(throwable: Throwable) {
        val messageException = getThrowableMessage(throwable)
        val message = messageException.message ?: view.getString(messageException.messageResId!!)
        with(view) {
            showOneButtonDialog(
                getString(R.string.dialog_error_title),
                message,
                getString(R.string.dialog_error_ok),
                null
            )
        }
    }

    fun getThrowableMessage(throwable: Throwable) =
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
