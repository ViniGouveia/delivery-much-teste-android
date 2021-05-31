package com.example.delivery_much_teste_android.ui.repositories.details

import dagger.Module
import dagger.Provides

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
@Module
class RepositoryDetailsModule {
    @Provides
    fun providesPresenter(
        view: RepositoryDetailsFragment
    ): RepositoryDetailsContract.Presenter = RepositoryDetailsPresenterImpl(view)

    @Provides
    fun providesInteractor(): RepositoryDetailsContract.Interactor =
        RepositoryDetailsInteractorImpl()
}
