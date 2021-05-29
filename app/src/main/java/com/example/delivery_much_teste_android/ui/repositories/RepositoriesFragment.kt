package com.example.delivery_much_teste_android.ui.repositories

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.base.BaseFragment
import com.example.delivery_much_teste_android.shared.model.Repository
import com.example.delivery_much_teste_android.ui.MainContract
import com.example.delivery_much_teste_android.ui.repositories.adapter.RepositoriesAdapter
import kotlinx.android.synthetic.main.repositories_fragment_layout.repositories_et_search
import kotlinx.android.synthetic.main.repositories_fragment_layout.repositories_ic_clear_search
import kotlinx.android.synthetic.main.repositories_fragment_layout.repositories_rv_repositories
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
                repositories_et_search.setText(EMPTY_STRING)
                presenter::resetList
            }
        }

        presenter.fetchRepositories()
    }

    override fun setRepositoriesList(repositories: List<Repository>) =
        repositoriesAdapter.setList(repositories)

    private fun setupRecyclerView() = repositories_rv_repositories.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = repositoriesAdapter.apply {
            callback = {}
        }
    }

    private fun setupEditText() = repositories_et_search.apply {
        doOnTextChanged { text, _, _, _ -> presenter.filterRepositoriesList(text.toString()) }
    }

    companion object {
        fun newInstance() = RepositoriesFragment()
        private const val EMPTY_STRING = ""
    }
}