package com.example.delivery_much_teste_android.ui.repositories

import com.example.delivery_much_teste_android.shared.base.BaseContract
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoriesUseCase

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
interface RepositoriesContract {

    interface View : BaseContract.ActionView {
        fun setRepositoriesList(repositories: List<Repository>)
        fun showRepositoriesButton()
        fun hideRepositoriesButton()
        fun showRepositoriesList()
        fun hideRepositoriesList()
    }

    interface Presenter : BaseContract.Presenter {
        fun fetchRepositories()
        fun filterRepositoriesList(filter: String)
        fun resetList()
    }

    interface Interactor : BaseContract.Interactor, FetchRepositoriesUseCase

    interface Router : BaseContract.Router
}