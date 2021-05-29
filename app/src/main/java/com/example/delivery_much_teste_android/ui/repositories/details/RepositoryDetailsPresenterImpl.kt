package com.example.delivery_much_teste_android.ui.repositories.details

import com.example.delivery_much_teste_android.shared.base.BasePresenter
import com.example.delivery_much_teste_android.shared.extensions.addTo
import com.example.delivery_much_teste_android.shared.model.RepositoryDetailsDisplay
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.shared.network.ThrowableHandler
import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoryDetailsPresenterImpl(
    view: RepositoryDetailsContract.View,
    schedulerProvider: SchedulerProvider,
    disposableProvider: DisposableProvider,
    throwableHandler: ThrowableHandler,
    private val interactor: RepositoryDetailsContract.Interactor
) : BasePresenter<RepositoryDetailsContract.View>(
    view, disposableProvider, schedulerProvider, throwableHandler
), RepositoryDetailsContract.Presenter {
    override fun fetchOwnerInfo(repository: Repository) {
        interactor.fetchRepositoryOwnerInfo(repository.repositoryOwnerUsername)
            .compose(observeOnUiAfterResult())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe({
                view.fillFields(
                    RepositoryDetailsDisplay(
                        it, repository.repositoryDescription, repository.repositoryName
                    )
                )
            }, ::showError)
            .addTo(disposableProvider!!)
    }
}