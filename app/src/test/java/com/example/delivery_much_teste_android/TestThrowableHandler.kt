package com.example.delivery_much_teste_android

import com.example.delivery_much_teste_android.shared.network.ExceptionMessage
import com.example.delivery_much_teste_android.shared.network.ThrowableHandler

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class TestThrowableHandler : ThrowableHandler {

    override fun getExceptionMessage(throwable: Throwable): ExceptionMessage =
        ExceptionMessage(
            code = "-2",
            messageResId = R.string.throwable_handler_generic_message
        )
}
