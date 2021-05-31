package com.example.delivery_much_teste_android.ui

import androidx.annotation.StringRes
import com.example.delivery_much_teste_android.shared.base.BaseContract

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
interface MainContract {

    interface Activity : BaseContract.ActivityView {
        fun showBackButton()
        fun hideBackButton()
        fun setToolbarTitle(@StringRes title: Int)
    }

    interface Presenter : BaseContract.Presenter

    interface Interactor : BaseContract.Interactor
}