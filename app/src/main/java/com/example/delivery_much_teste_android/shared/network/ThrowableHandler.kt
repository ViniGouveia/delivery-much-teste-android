package com.example.delivery_much_teste_android.shared.network

import io.reactivex.SingleTransformer

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
interface ThrowableHandler {

    fun getExceptionMessage(throwable: Throwable): ExceptionMessage

    fun isWrongSecurityPasswordSentOnRequest(throwable: Throwable): Boolean

    fun isNotAuthorized(throwable: Throwable): Boolean

    fun isRefreshTokenException(throwable: Throwable): Boolean

    fun isAppOldVersion(throwable: Throwable): Boolean

    fun isNotPreAuthorized(
        throwable: Throwable,
        transformer: SingleTransformer<Unit, Unit>
    ): Boolean
}
