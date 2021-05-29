package com.example.delivery_much_teste_android.shared.usecase

import com.example.delivery_much_teste_android.shared.model.Repository
import com.example.delivery_much_teste_android.shared.service.RepositoryService
import io.reactivex.Single

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
interface FetchRepositoriesUseCase {
    fun fetchRepositories(): Single<List<Repository>>
}

class FetchRepositoriesUseCaseImpl(
    private val service: RepositoryService
) : FetchRepositoriesUseCase {
    override fun fetchRepositories(): Single<List<Repository>> =
        service.fetchRepositories().map { it.map(::Repository) }
//        Single.just(
//        listOf(
//            Repository(
//                "Teste 1",
//                "Teste 1"
//            ),
//            Repository(
//                "Teste 2",
//                "Teste 2"
//            ),
//            Repository(
//                "Teste 3",
//                "Teste 3"
//            ),
//            Repository(
//                "Teste 4",
//                "Teste 4"
//            ),
//            Repository(
//                "Teste 5",
//                "Teste 5"
//            ),
//            Repository(
//                "Teste 6",
//                "Teste 6"
//            ),
//            Repository(
//                "Teste 7",
//                "Teste 7"
//            )
//        )
//    ).delay(3000L, TimeUnit.MILLISECONDS)
}
