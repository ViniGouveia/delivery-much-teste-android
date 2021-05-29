package com.example.delivery_much_teste_android.ui.repositories

import com.example.delivery_much_teste_android.shared.base.BasePresenter
import com.example.delivery_much_teste_android.shared.extensions.addTo
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.shared.network.ThrowableHandler
import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoriesPresenterImpl(
    view: RepositoriesContract.View,
    schedulerProvider: SchedulerProvider,
    disposableProvider: DisposableProvider,
    throwableHandler: ThrowableHandler,
    private val interactor: RepositoriesContract.Interactor,
    private val router: RepositoriesContract.Router
) : BasePresenter<RepositoriesContract.View>(
    view,
    disposableProvider,
    schedulerProvider,
    throwableHandler
), RepositoriesContract.Presenter {

    private lateinit var repositoriesList: List<Repository>

    override fun fetchRepositories() {
        interactor.fetchRepositories()
            .compose(observeOnUiAfterResult())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe({
                repositoriesList = it
                with(view) {
                    setRepositoriesList(it)
                    showRepositoriesList()
                    hideRepositoriesButton()
                }
            }, ::showError)
            .addTo(disposableProvider!!)
    }

    override fun filterRepositoriesList(filter: String) {
        view.setRepositoriesList(repositoriesList.filter { it.repositoryName.contains(filter) })
    }

    override fun resetList() {
        view.setRepositoriesList(repositoriesList)
    }
}