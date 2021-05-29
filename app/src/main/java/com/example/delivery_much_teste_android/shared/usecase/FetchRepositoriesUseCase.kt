package com.example.delivery_much_teste_android.shared.usecase

import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.shared.service.RepositoryService
import io.reactivex.Single
import java.util.concurrent.TimeUnit

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
        //service.fetchRepositories(FIRST_PAGE).map { it.map(::Repository) }
        Single.just(
            listOf(
                Repository(
                    1,
                    "username1",
                    "https://image.flaticon.com/icons/png/512/147/147144.png",
                    "https://www.google.com.br",
                    "Repository Name 1",
                    "Repository Description 1"
                ),
                Repository(
                    2,
                    "username2",
                    "https://image.flaticon.com/icons/png/512/147/147144.png",
                    "https://www.google.com.br",
                    "Repository Name 2",
                    "Repository Description 2"
                ),
                Repository(
                    3,
                    "username3",
                    "https://image.flaticon.com/icons/png/512/147/147144.png",
                    "https://www.google.com.br",
                    "Repository Name 3",
                    "Repository Description 3"
                ),
                Repository(
                    4,
                    "username4",
                    "https://image.flaticon.com/icons/png/512/147/147144.png",
                    "https://www.google.com.br",
                    "Repository Name 4",
                    "Repository Description 4"
                ),
                Repository(
                    5,
                    "username5",
                    "https://image.flaticon.com/icons/png/512/147/147144.png",
                    "https://www.google.com.br",
                    "Repository Name 5",
                    "Repository Description 5"
                ),
                Repository(
                    6,
                    "username6",
                    "https://image.flaticon.com/icons/png/512/147/147144.png",
                    "https://www.google.com.br",
                    "Repository Name 6",
                    "Repository Description 6"
                ),
                Repository(
                    7,
                    "username7",
                    "https://image.flaticon.com/icons/png/512/147/147144.png",
                    "https://www.google.com.br",
                    "Repository Name 7",
                    "Repository Description 7"
                )
            )
        ).delay(3000L, TimeUnit.MILLISECONDS)

    private companion object {
        const val FIRST_PAGE = 1
    }
}
