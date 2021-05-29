package com.example.delivery_much_teste_android.ui.repositories.details

import com.example.delivery_much_teste_android.shared.network.ThrowableHandler
import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoryOwnerInfoUseCase
import dagger.Module
import dagger.Provides

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
@Module
class RepositoryDetailsModule {
    @Provides
    fun providesPresenter(
        view: RepositoryDetailsFragment,
        schedulerProvider: SchedulerProvider,
        disposableProvider: DisposableProvider,
        throwableHandler: ThrowableHandler,
        interactor: RepositoryDetailsContract.Interactor
    ): RepositoryDetailsContract.Presenter = RepositoryDetailsPresenterImpl(
        view, schedulerProvider, disposableProvider, throwableHandler, interactor
    )

    @Provides
    fun providesInteractor(
        fetchRepositoryOwnerInfoUseCase: FetchRepositoryOwnerInfoUseCase
    ): RepositoryDetailsContract.Interactor =
        RepositoryDetailsInteractorImpl(fetchRepositoryOwnerInfoUseCase)
}
