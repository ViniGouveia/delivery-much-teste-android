package com.example.delivery_much_teste_android.ui.repositoryowner

import com.example.delivery_much_teste_android.shared.base.BaseContract

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
interface RepositoryOwnerContract {
    interface View : BaseContract.ActionView

    interface Presenter : BaseContract.Presenter

    interface Interactor : BaseContract.Interactor
}
