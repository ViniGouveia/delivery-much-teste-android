package com.example.delivery_much_teste_android.ui.repositories.details

import com.example.delivery_much_teste_android.shared.base.BasePresenter

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoryDetailsPresenterImpl(view: RepositoryDetailsContract.View) :
    BasePresenter<RepositoryDetailsContract.View>(view), RepositoryDetailsContract.Presenter
