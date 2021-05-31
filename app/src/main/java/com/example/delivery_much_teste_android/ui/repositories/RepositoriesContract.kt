package com.example.delivery_much_teste_android.ui.repositories

import com.example.delivery_much_teste_android.shared.base.BaseContract
import com.example.delivery_much_teste_android.shared.model.RepositoryDetailsDisplay
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCase
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoryOwnerInfoUseCase

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
interface RepositoriesContract {

    interface View : BaseContract.ActionView {
        fun setRepositoriesList(repositories: List<Repository>)
        fun showEmptyListAlert()
        fun hideEmptyListAlert()
        fun openDetailsBottomSheet(display: RepositoryDetailsDisplay)
    }

    interface Presenter : BaseContract.Presenter {
        fun fetchRepositories()
        fun filterRepositoriesList(filter: String)
        fun resetList()
        fun fetchOwnerInfo(repository: Repository)
        fun openUserProfileFragment(profileUrl: String)
    }

    interface Interactor : BaseContract.Interactor, FetchRepositoriesUseCase,
        FetchRepositoryOwnerInfoUseCase

    interface Router : BaseContract.Router {
        fun routerToUserProfile(profileUrl: String)
    }
}
