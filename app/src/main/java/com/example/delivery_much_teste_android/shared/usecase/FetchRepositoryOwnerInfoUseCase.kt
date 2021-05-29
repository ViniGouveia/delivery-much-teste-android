package com.example.delivery_much_teste_android.shared.usecase

import com.example.delivery_much_teste_android.shared.model.repositoryowner.RepositoryOwnerInfo
import com.example.delivery_much_teste_android.shared.service.RepositoryService
import io.reactivex.Single

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
interface FetchRepositoryOwnerInfoUseCase {
    fun fetchRepositoryOwnerInfo(ownerUsername: String): Single<RepositoryOwnerInfo>
}

class FetchRepositoryOwnerInfoUseCaseImpl(
    private val service: RepositoryService
) : FetchRepositoryOwnerInfoUseCase {
    override fun fetchRepositoryOwnerInfo(ownerUsername: String) = Single.just(
        RepositoryOwnerInfo(
            "José da Silva",
            "https://www.google.com.br/",
            "https://image.flaticon.com/icons/png/512/147/147144.png"
        )
    )
}
