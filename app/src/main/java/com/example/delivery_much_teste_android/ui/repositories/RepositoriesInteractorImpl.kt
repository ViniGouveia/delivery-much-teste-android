package com.example.delivery_much_teste_android.ui.repositories

import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCase
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoryOwnerInfoUseCase

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoriesInteractorImpl(
    private val fetchRepositoriesUseCase: FetchRepositoriesUseCase,
    fetchRepositoryOwnerInfoUseCase: FetchRepositoryOwnerInfoUseCase
) : RepositoriesContract.Interactor,
    FetchRepositoriesUseCase by fetchRepositoriesUseCase,
    FetchRepositoryOwnerInfoUseCase by fetchRepositoryOwnerInfoUseCase
