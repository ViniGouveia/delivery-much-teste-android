package com.example.delivery_much_teste_android.ui.repositories

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.base.BaseFragment
import com.example.delivery_much_teste_android.shared.extensions.emptyString
import com.example.delivery_much_teste_android.shared.model.RepositoryDetailsDisplay
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.ui.MainContract
import com.example.delivery_much_teste_android.ui.repositories.adapter.RepositoriesAdapter
import com.example.delivery_much_teste_android.ui.repositories.details.RepositoryDetailsFragment
import kotlinx.android.synthetic.main.repositories_fragment_layout.repositories_et_search
import kotlinx.android.synthetic.main.repositories_fragment_layout.repositories_ic_clear_search
import kotlinx.android.synthetic.main.repositories_fragment_layout.repositories_rv_repositories
import kotlinx.android.synthetic.main.repositories_fragment_layout.repositories_tv_empty_list_alert
import javax.inject.Inject

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoriesFragment : BaseFragment<MainContract.Activity, RepositoriesContract.Presenter>(),
    RepositoriesContract.View {

    @Inject
    override lateinit var presenter: RepositoriesContract.Presenter

    @Inject
    lateinit var repositoriesAdapter: RepositoriesAdapter

    override var layoutRes = R.layout.repositories_fragment_layout

    override fun initialize() {
        setupEditText()
        setupRecyclerView()

        repositories_ic_clear_search.apply {
            setOnClickListener {
                repositories_et_search.setText(String.emptyString())
                presenter::resetList
            }
        }

        presenter.fetchRepositories()
    }

    override fun onResume() {
        super.onResume()
        parentActivity.hideBackButton()
        parentActivity.setToolbarTitle(R.string.toolbar_title)
    }

    override fun setRepositoriesList(repositories: List<Repository>) =
        repositoriesAdapter.setList(repositories)

    override fun showEmptyListAlert() {
        repositories_tv_empty_list_alert.visibility = VISIBLE
        repositories_rv_repositories.visibility = GONE
    }

    override fun hideEmptyListAlert() {
        repositories_tv_empty_list_alert.visibility = GONE
        repositories_rv_repositories.visibility = VISIBLE
    }

    override fun openDetailsBottomSheet(display: RepositoryDetailsDisplay) {
        val repositoryDetailsBottomSheet =
            RepositoryDetailsFragment.newInstance(display, ::openOwnerProfile)
        repositoryDetailsBottomSheet.show(
            parentActivity.getSupportFragmentManager(),
            repositoryDetailsBottomSheet.tag
        )
    }

    private fun setupRecyclerView() = repositories_rv_repositories.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = repositoriesAdapter.apply {
            callback = presenter::fetchOwnerInfo
        }
    }

    private fun openOwnerProfile(profileUrl: String) = presenter.openUserProfileFragment(profileUrl)

    private fun setupEditText() = repositories_et_search.apply {
        doOnTextChanged { text, _, _, _ -> presenter.filterRepositoriesList(text.toString()) }
    }

    companion object {
        fun newInstance() = RepositoriesFragment()
    }
}
