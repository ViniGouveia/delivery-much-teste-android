package com.example.delivery_much_teste_android.shared.model.repositoryowner

import com.google.gson.annotations.SerializedName

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class RepositoryOwnerInfoResponse(
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val profileUrl: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
