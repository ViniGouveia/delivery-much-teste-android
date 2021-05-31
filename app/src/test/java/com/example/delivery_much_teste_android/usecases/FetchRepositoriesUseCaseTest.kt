package com.example.delivery_much_teste_android.usecases

import com.example.delivery_much_teste_android.UseCaseTest
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.shared.model.repository.RepositoryOwnerResponse
import com.example.delivery_much_teste_android.shared.model.repository.RepositoryResponse
import com.example.delivery_much_teste_android.shared.service.RepositoryService
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCase
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCaseImpl
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class FetchRepositoriesUseCaseTest : UseCaseTest() {

    private lateinit var useCase: FetchRepositoriesUseCase

    private val service = mockk<RepositoryService>()

    override fun initialize() {
        useCase = FetchRepositoriesUseCaseImpl(service)
    }

    @Test
    fun `should fetch repositories successfully`() {
        val serviceResponse = listOf(
            RepositoryResponse(
                1,
                RepositoryOwnerResponse("username"),
                "name",
                "description"
            )
        )
        val response = listOf(
            Repository(
                1,
                "username",
                "name",
                "description"
            )
        )

        every { service.fetchRepositories() } returns Single.just(serviceResponse)

        useCase.fetchRepositories()
            .test()
            .assertResult(response)

        verify(exactly = 1) {
            service.fetchRepositories()
        }

        confirmVerified(service)
    }

    @Test
    fun `should return error when fetching repositories`() {
        val error = Throwable()

        every { service.fetchRepositories() } returns Single.error(error)

        useCase.fetchRepositories()
            .test()
            .assertError(error)

        verify(exactly = 1) {
            service.fetchRepositories()
        }

        confirmVerified(service)
    }
}
