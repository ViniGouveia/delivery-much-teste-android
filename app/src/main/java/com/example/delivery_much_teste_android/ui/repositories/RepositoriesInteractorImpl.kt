package com.example.delivery_much_teste_android.ui.repositories

import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCase

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoriesInteractorImpl(
    private val fetchRepositoriesUseCase: FetchRepositoriesUseCase
) : RepositoriesContract.Interactor,
    FetchRepositoriesUseCase by fetchRepositoriesUseCase
