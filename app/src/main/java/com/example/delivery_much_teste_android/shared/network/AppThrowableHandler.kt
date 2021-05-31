package com.example.delivery_much_teste_android.shared.network

import android.content.Context
import com.example.delivery_much_teste_android.R
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class AppThrowableHandler(
    private var retrofit: Retrofit,
    private var context: Context
) : ThrowableHandler {

    override fun getExceptionMessage(throwable: Throwable): ExceptionMessage {
        val exceptionMessage: ExceptionMessage

        Timber.d(throwable)

        exceptionMessage = when (throwable) {
            is HttpException -> {
                val responseMessage = convertResponse(throwable.response()!!)
                return ExceptionMessage(
                    code = responseMessage?.code,
                    message = responseMessage?.message
                )
            }
            is IOException, is UnknownHostException -> ExceptionMessage(
                code = MESSAGE_CODE_NO_CONNECTION,
                messageResId = R.string.throwable_handler_no_network
            )
            is NullPointerException -> ExceptionMessage(
                code = DEFAULT_MESSAGE_CODE,
                message = throwable.message
            )
            else -> ExceptionMessage(
                code = DEFAULT_MESSAGE_CODE,
                messageResId = R.string.throwable_handler_generic_message
            )
        }

        return exceptionMessage
    }

    private fun convertResponse(response: Response<*>): GenericResponse? {
        val converter: Converter<ResponseBody, GenericResponse?> = retrofit.responseBodyConverter(
            GenericResponse::class.java, arrayOfNulls<Annotation>(0)
        )

        try {
            return converter.convert(response.errorBody()!!)
        } catch (exception: Exception) {
            when (exception) {
                is IOException,
                is NullPointerException,
                is IllegalStateException,
                is JsonSyntaxException -> Timber.e(exception)
            }
        }

        return GenericResponse(message = context.getString(R.string.throwable_handler_generic_message))
    }

    private companion object Constants {
        private const val MESSAGE_CODE_NO_CONNECTION = "-2"
        private const val DEFAULT_MESSAGE_CODE = "-1"
    }
}
