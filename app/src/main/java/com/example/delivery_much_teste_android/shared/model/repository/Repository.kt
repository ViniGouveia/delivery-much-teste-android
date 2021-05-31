package com.example.delivery_much_teste_android.shared.model.repository

import com.example.delivery_much_teste_android.shared.extensions.emptyString

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class Repository(
    val repositoryId: Int,
    val repositoryOwnerUsername: String,
    val repositoryName: String,
    val repositoryDescription: String
) {
    constructor(response: RepositoryResponse) : this(
        response.repositoryId,
        response.repositoryOwner.username,
        response.repositoryName,
        response.repositoryDescription ?: String.emptyString()
    )
}
