package com.example.delivery_much_teste_android.shared.network

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
data class ExceptionMessage(
    var code: String? = null,
    var messageResId: Int? = null,
    var message: String? = null
)
