package com.example.delivery_much_teste_android.ui

import com.example.delivery_much_teste_android.shared.base.BaseContract

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
interface MainContract {

    interface Activity : BaseContract.ActivityView

    interface Presenter : BaseContract.Presenter

    interface Interactor : BaseContract.Interactor
}