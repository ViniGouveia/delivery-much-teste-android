package com.example.delivery_much_teste_android.shared.model.repository

import com.google.gson.annotations.SerializedName

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class RepositoryOwnerResponse(
    @SerializedName("login") val username: String
)
