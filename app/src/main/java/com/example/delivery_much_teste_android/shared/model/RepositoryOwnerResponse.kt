package com.example.delivery_much_teste_android.shared.model

import com.google.gson.annotations.SerializedName

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class RepositoryOwnerResponse(
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val profileUrl: String,
    @SerializedName("login") val username: String
)
