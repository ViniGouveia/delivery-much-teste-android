package com.example.delivery_much_teste_android.ui.repositories.details

import com.example.delivery_much_teste_android.shared.base.BaseContract
import com.example.delivery_much_teste_android.shared.model.RepositoryDetailsDisplay
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.shared.usecase.FetchRepositoryOwnerInfoUseCase

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
interface RepositoryDetailsContract {

    interface View : BaseContract.ActionView {
        fun fillFields(display: RepositoryDetailsDisplay)
    }

    interface Presenter : BaseContract.Presenter {
        fun fetchOwnerInfo(repository: Repository)
    }

    interface Interactor : BaseContract.Interactor, FetchRepositoryOwnerInfoUseCase

}
