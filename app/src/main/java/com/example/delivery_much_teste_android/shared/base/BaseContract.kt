package com.example.delivery_much_teste_android.shared.base

import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.google.android.material.appbar.MaterialToolbar

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Suppress("TooManyFunctions", "LongParameterList")
interface BaseContract {

    interface ActionView {
        fun showLoading(textRes: Int = 0)
        fun hideLoading()
        fun getString(@StringRes stringRes: Int): String
        fun getString(@StringRes stringRes: Int, vararg args: Any?): String
        fun showOneButtonDialog(
            title: String,
            body: String,
            buttonText: String,
            callback: DialogInterface.OnClickListener? = null
        )
    }

    interface ActivityView : ActionView {
        var toolbar: MaterialToolbar?
        var toolbarTitle: String
        fun setRootFragment(fragment: BaseFragment<*, *>)
        fun finish()
        fun getSupportFragmentManager(): FragmentManager
        fun getPackageManager(): PackageManager
        fun getPackageName(): String
        fun openFragment(fragment: BaseFragment<*, *>)
    }

    interface Presenter {
        fun interrupt()
        fun detach()
    }

    interface Interactor

    interface Router
}
