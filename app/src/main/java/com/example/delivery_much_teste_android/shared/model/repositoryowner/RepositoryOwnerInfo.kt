package com.example.delivery_much_teste_android.shared.model.repositoryowner

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class RepositoryOwnerInfo(
    val name: String,
    val profileUrl: String,
    val avatarUrl: String
) {
    constructor(response: RepositoryOwnerInfoResponse) : this(
        response.name,
        response.profileUrl,
        response.avatarUrl
    )
}
