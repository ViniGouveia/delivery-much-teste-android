package com.example.delivery_much_teste_android.usecases

import com.example.delivery_much_teste_android.UseCaseTest
import com.example.delivery_much_teste_android.shared.model.repositoryowner.RepositoryOwnerInfo
import com.example.delivery_much_teste_android.shared.model.repositoryowner.RepositoryOwnerInfoResponse
import com.example.delivery_much_teste_android.shared.service.RepositoryService
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoryOwnerInfoUseCase
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoryOwnerInfoUseCaseImpl
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class FetchRepositoryOwnerInfoUseCaseTest : UseCaseTest() {

    private lateinit var useCase: FetchRepositoryOwnerInfoUseCase

    private val service = mockk<RepositoryService>()

    override fun initialize() {
        useCase = FetchRepositoryOwnerInfoUseCaseImpl(service)
    }

    @Test
    fun `should fetch repositories successfully`() {
        val serviceResponse = RepositoryOwnerInfoResponse(
            "name",
            "profileUrl",
            "avatarUrl"
        )
        val response = RepositoryOwnerInfo(
            "name",
            "profileUrl",
            "avatarUrl"
        )

        every { service.fetchRepositoryOwnerInfo(any()) } returns Single.just(serviceResponse)

        useCase.fetchRepositoryOwnerInfo("username")
            .test()
            .assertResult(response)

        verify(exactly = 1) {
            service.fetchRepositoryOwnerInfo("username")
        }

        confirmVerified(service)
    }

    @Test
    fun `should return error when fetching repositories`() {
        val error = Throwable()

        every { service.fetchRepositoryOwnerInfo(any()) } returns Single.error(error)

        useCase.fetchRepositoryOwnerInfo("username")
            .test()
            .assertError(error)

        verify(exactly = 1) {
            service.fetchRepositoryOwnerInfo("username")
        }

        confirmVerified(service)
    }


}
