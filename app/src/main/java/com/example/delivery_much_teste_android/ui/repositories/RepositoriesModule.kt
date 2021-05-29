package com.example.delivery_much_teste_android.ui.repositories

import com.example.delivery_much_teste_android.shared.network.ThrowableHandler
import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCase
import com.example.delivery_much_teste_android.ui.repositories.adapter.RepositoriesAdapter
import dagger.Module
import dagger.Provides

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
@Module
class RepositoriesModule {
    @Provides
    fun providePresenter(
        view: RepositoriesFragment,
        schedulerProvider: SchedulerProvider,
        disposableProvider: DisposableProvider,
        throwableHandler: ThrowableHandler,
        interactor: RepositoriesContract.Interactor,
        router: RepositoriesContract.Router
    ): RepositoriesContract.Presenter = RepositoriesPresenterImpl(
        view,
        schedulerProvider,
        disposableProvider,
        throwableHandler,
        interactor,
        router
    )

    @Provides
    fun provideInteractor(
        fetchRepositoriesUseCase: FetchRepositoriesUseCase
    ): RepositoriesContract.Interactor = RepositoriesInteractorImpl(
        fetchRepositoriesUseCase
    )

    @Provides
    fun provideAdapter(
        view: RepositoriesFragment
    ): RepositoriesAdapter = RepositoriesAdapter(view.requireContext())

    @Provides
    fun provideRouter(): RepositoriesContract.Router = RepositoriesRouterImpl()
}