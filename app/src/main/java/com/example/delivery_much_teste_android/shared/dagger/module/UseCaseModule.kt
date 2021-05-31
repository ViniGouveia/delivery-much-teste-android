package com.example.delivery_much_teste_android.shared.dagger.module

import com.example.delivery_much_teste_android.shared.service.RepositoryService
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCase
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCaseImpl
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoryOwnerInfoUseCase
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoryOwnerInfoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Module
object UseCaseModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideFetchRepositoriesUseCase(
        service: RepositoryService
    ): FetchRepositoriesUseCase =
        FetchRepositoriesUseCaseImpl(service)

    @Provides
    @Reusable
    @JvmStatic
    fun provideFetchRepositoryOwnerInfoUseCase(
        service: RepositoryService
    ): FetchRepositoryOwnerInfoUseCase =
        FetchRepositoryOwnerInfoUseCaseImpl(service)
}