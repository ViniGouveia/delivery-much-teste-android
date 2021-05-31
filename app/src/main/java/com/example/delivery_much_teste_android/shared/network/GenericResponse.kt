package com.example.delivery_much_teste_android.shared.network

import com.example.delivery_much_teste_android.shared.extensions.emptyString

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class GenericResponse(
    var code: String = String.emptyString(),
    var message: String = String.emptyString()
)
