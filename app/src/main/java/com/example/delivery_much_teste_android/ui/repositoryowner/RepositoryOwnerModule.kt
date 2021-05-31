package com.example.delivery_much_teste_android.ui.repositoryowner

import dagger.Module
import dagger.Provides

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
@Module
class RepositoryOwnerModule {
    @Provides
    fun providePresenter(view: RepositoryOwnerFragment): RepositoryOwnerContract.Presenter =
        RepositoryOwnerPresenterImpl(view)

    @Provides
    fun providesInteractor(): RepositoryOwnerContract.Interactor =
        RepositoryOwnerInteractorImpl()
}
