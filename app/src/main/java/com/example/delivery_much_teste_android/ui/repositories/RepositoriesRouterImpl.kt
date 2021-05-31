package com.example.delivery_much_teste_android.ui.repositories

import androidx.fragment.app.FragmentActivity
import com.example.delivery_much_teste_android.shared.base.BaseRouter
import com.example.delivery_much_teste_android.ui.MainContract
import com.example.delivery_much_teste_android.ui.repositoryowner.RepositoryOwnerFragment

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoriesRouterImpl(activity: FragmentActivity) :
    BaseRouter<MainContract.Activity>(activity),
    RepositoriesContract.Router {
    override fun routerToUserProfile(profileUrl: String) =
        parentActivity.openFragment(RepositoryOwnerFragment.newInstance(profileUrl))
}
