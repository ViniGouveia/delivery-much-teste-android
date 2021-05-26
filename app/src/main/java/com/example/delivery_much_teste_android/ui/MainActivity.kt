package com.example.delivery_much_teste_android.ui

import com.example.delivery_much_teste_android.shared.base.BaseActivity
import javax.inject.Inject

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.Activity {

    @Inject
    override lateinit var presenter: MainContract.Presenter

    override var layoutRes: Int
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun initialize() {
        TODO("Not yet implemented")
    }

    override fun executeActionIfViewIsVisible(action: () -> Unit) {
        TODO("Not yet implemented")
    }
}