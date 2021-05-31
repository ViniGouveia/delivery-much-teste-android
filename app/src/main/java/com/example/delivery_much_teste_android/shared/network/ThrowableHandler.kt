package com.example.delivery_much_teste_android.shared.network

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
interface ThrowableHandler {

    fun getExceptionMessage(throwable: Throwable): ExceptionMessage
}
