package com.example.delivery_much_teste_android.shared.model.repository

import com.example.delivery_much_teste_android.shared.model.repository.RepositoryOwnerResponse
import com.google.gson.annotations.SerializedName

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class RepositoryResponse(
    @SerializedName("id") val repositoryId: Int,
    @SerializedName("owner") val repositoryOwner: RepositoryOwnerResponse,
    @SerializedName("name") val repositoryName: String,
    @SerializedName("description") val repositoryDescription: String
)
