package com.example.delivery_much_teste_android.shared.model

import com.example.delivery_much_teste_android.shared.model.repositoryowner.RepositoryOwnerInfo

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
data class RepositoryDetailsDisplay(
    val repositoryName: String,
    val repositoryDescription: String,
    val repositoryOwnerName: String,
    val repositoryOwnerUrl: String,
    val repositoryOwnerAvatar: String
) {
    constructor(
        repositoryOwner: RepositoryOwnerInfo,
        repositoryDescription: String,
        repositoryName: String
    ) : this(
        repositoryName,
        repositoryDescription,
        repositoryOwner.name,
        repositoryOwner.profileUrl,
        repositoryOwner.avatarUrl
    )
}
