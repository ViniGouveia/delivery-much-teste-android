package com.example.delivery_much_teste_android.ui.repositories

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
                with(view) {
                    if (it.isEmpty()) {
                        showEmptyListAlert()
                    } else {
                        repositoriesList = it
                        hideEmptyListAlert()
                        setRepositoriesList(it)
                    }
                }
            }, ::showError)
            .addTo(disposableProvider!!)
    }

    override fun filterRepositoriesList(filter: String) {
        val filteredList = repositoriesList.filter { it.repositoryName.contains(filter) }
        with(view) {
            if (filteredList.isEmpty()) {
                showEmptyListAlert()
            } else {
                hideEmptyListAlert()
                setRepositoriesList(filteredList)
            }
        }
    }

    override fun resetList() {
        view.setRepositoriesList(repositoriesList)
    }

    override fun fetchOwnerInfo(repository: Repository) {
        interactor.fetchRepositoryOwnerInfo(repository.repositoryOwnerUsername)
            .compose(observeOnUiAfterResult())
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe({
                view.openDetailsBottomSheet(
                    RepositoryDetailsDisplay(
                        it, repository.repositoryDescription, repository.repositoryName
                    )
                )
            }, ::showError)
            .addTo(disposableProvider!!)
    }

    override fun openUserProfileFragment(profileUrl: String) =
        router.routerToUserProfile(profileUrl)
}
