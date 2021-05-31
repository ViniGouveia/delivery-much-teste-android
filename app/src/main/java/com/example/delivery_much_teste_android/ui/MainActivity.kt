package com.example.delivery_much_teste_android.ui

import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.base.BaseActivity
import com.example.delivery_much_teste_android.ui.repositories.RepositoriesFragment
import kotlinx.android.synthetic.main.main_activity_layout.main_toolbar
import kotlinx.android.synthetic.main.main_activity_layout.main_tv_toolbar_title
import javax.inject.Inject

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.Activity {

    @Inject
    override lateinit var presenter: MainContract.Presenter

    override var layoutRes = R.layout.main_activity_layout

    override var frameLayoutId = R.id.main_fl_content

    override fun initialize() {
        toolbar = main_toolbar
        main_tv_toolbar_title.text = getString(R.string.toolbar_title)

        setRootFragment(RepositoriesFragment.newInstance())
    }

    override fun showBackButton() {
        toolbar?.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
    }

    override fun hideBackButton() {
        toolbar?.navigationIcon = null
    }

    override fun setToolbarTitle(@StringRes title: Int) {
        main_tv_toolbar_title.text = getText(title)
    }
}