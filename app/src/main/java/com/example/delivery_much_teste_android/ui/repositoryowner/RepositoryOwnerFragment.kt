package com.example.delivery_much_teste_android.ui.repositoryowner

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.base.BaseFragment
import com.example.delivery_much_teste_android.ui.MainContract
import kotlinx.android.synthetic.main.repository_owner_fragment_layout.repository_owner_wv_profile_page
import javax.inject.Inject

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class RepositoryOwnerFragment :
    BaseFragment<MainContract.Activity, RepositoryOwnerContract.Presenter>(),
    RepositoryOwnerContract.View {

    @Inject
    override lateinit var presenter: RepositoryOwnerContract.Presenter

    private lateinit var profileUrl: String

    override var layoutRes = R.layout.repository_owner_fragment_layout

    override fun initialize() {
        parentActivity.setToolbarTitle(R.string.repository_owner_toolbar_title)
        parentActivity.showBackButton()

        repository_owner_wv_profile_page.apply {
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) =
                    showLoading()

                override fun onPageFinished(view: WebView, url: String) = hideLoading()
            }
            loadUrl(profileUrl)
        }
    }

    companion object {
        fun newInstance(profileUrl: String) = RepositoryOwnerFragment().apply {
            this.profileUrl = profileUrl
        }
    }
}
