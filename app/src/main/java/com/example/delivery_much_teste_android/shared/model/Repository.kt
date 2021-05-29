package com.example.delivery_much_teste_android.shared.model

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class Repository(
    val repositoryId: Int,
    val repositoryOwnerUsername: String,
    val repositoryOwnerAvatarUrl: String,
    val repositoryOwnerProfileUrl: String,
    val repositoryName: String,
    val repositoryDescription: String
) {
    constructor(response: RepositoryResponse) : this(
        response.repositoryId,
        response.repositoryOwner.username,
        response.repositoryOwner.avatarUrl,
        response.repositoryOwner.profileUrl,
        response.repositoryName,
        response.repositoryDescription
    )
}
