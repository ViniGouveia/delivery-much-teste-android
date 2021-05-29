package com.example.delivery_much_teste_android.ui.repositories.details

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.base.BaseBottomSheetDialogFragment
import com.example.delivery_much_teste_android.shared.model.RepositoryDetailsDisplay
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.ui.MainContract
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.repository_details_layout.repository_details_iv_owner_avatar
import kotlinx.android.synthetic.main.repository_details_layout.repository_details_tv_owner_name
import kotlinx.android.synthetic.main.repository_details_layout.repository_details_tv_repository_details
import kotlinx.android.synthetic.main.repository_details_layout.repository_details_tv_repository_name
import kotlinx.android.synthetic.main.repository_details_layout.repository_details_tv_visit_profile
import javax.inject.Inject

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoryDetailsFragment :
    BaseBottomSheetDialogFragment<MainContract.Activity, RepositoryDetailsContract.Presenter>(),
    RepositoryDetailsContract.View {

    @Inject
    override lateinit var presenter: RepositoryDetailsContract.Presenter

    override val layoutRes = R.layout.repository_details_layout

    private lateinit var repository: Repository
    private lateinit var callback: ((String) -> Unit)

    override fun getTheme() = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), theme)

    override fun initialize() {
        presenter.fetchOwnerInfo(repository)
    }

    override fun fillFields(display: RepositoryDetailsDisplay) {
        repository_details_iv_owner_avatar.apply {
            Glide.with(requireContext())
                .load(display.repositoryOwnerAvatar)
                .into(this)
        }

        repository_details_tv_owner_name.text = display.repositoryOwnerName
        repository_details_tv_repository_name.text = display.repositoryName
        repository_details_tv_repository_details.text = display.repositoryDescription

        repository_details_tv_visit_profile.setOnClickListener {
            callback.invoke(display.repositoryOwnerUrl)
        }
    }

    companion object {
        fun newInstance(
            repository: Repository,
            callback: ((String) -> Unit)
        ) = RepositoryDetailsFragment().apply {
            this.repository = repository
            this.callback = callback
        }
    }
}