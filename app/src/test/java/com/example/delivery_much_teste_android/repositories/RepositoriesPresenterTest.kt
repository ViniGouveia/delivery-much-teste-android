package com.example.delivery_much_teste_android.repositories

import com.example.delivery_much_teste_android.BaseUnitTest
import com.example.delivery_much_teste_android.shared.model.RepositoryDetailsDisplay
import com.example.delivery_much_teste_android.shared.model.repository.Repository
import com.example.delivery_much_teste_android.shared.model.repositoryowner.RepositoryOwnerInfo
import com.example.delivery_much_teste_android.ui.repositories.RepositoriesContract
import com.example.delivery_much_teste_android.ui.repositories.RepositoriesPresenterImpl
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class RepositoriesPresenterTest : BaseUnitTest() {

    private lateinit var presenter: RepositoriesContract.Presenter

    private val view = mockk<RepositoriesContract.View>()
    private val interactor = mockk<RepositoriesContract.Interactor>()
    private val router = mockk<RepositoriesContract.Router>()

    private val repositories = listOf(
        Repository(
            1,
            "username",
            "name",
            "description"
        ),
        Repository(
            2,
            "username1",
            "name1",
            "description1"
        )
    )

    override fun initialize() {
        presenter = RepositoriesPresenterImpl(
            view,
            schedulerProvider,
            disposableProvider,
            throwableHandler,
            interactor,
            router
        )
    }

    @Test
    fun `should fetch public repositories successfully`() {
        every { interactor.fetchRepositories() } returns Single.just(repositories)
        every { view.showLoading() } just Runs
        every { view.hideLoading() } just Runs
        every { view.setRepositoriesList(any()) } just Runs
        every { view.hideEmptyListAlert() } just Runs

        presenter.fetchRepositories()
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchRepositories()
            with(view) {
                showLoading()
                hideLoading()
                setRepositoriesList(repositories)
                hideEmptyListAlert()
            }
        }

        confirmVerified(view, interactor, router)
    }

    @Test
    fun `should fetch empty public repositories list and show alert successfully`() {
        every { interactor.fetchRepositories() } returns Single.just(listOf())
        every { view.showLoading() } just Runs
        every { view.hideLoading() } just Runs
        every { view.setRepositoriesList(any()) } just Runs
        every { view.showEmptyListAlert() } just Runs

        presenter.fetchRepositories()
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchRepositories()
            with(view) {
                showLoading()
                hideLoading()
                showEmptyListAlert()
            }
        }

        confirmVerified(view, interactor, router)
    }

    @Test
    fun `should return error when fetching repositories`() {
        every { interactor.fetchRepositories() } returns Single.error(Throwable())
        every { view.showLoading() } just Runs
        every { view.hideLoading() } just Runs
        every { view.getString(any()) } returns ""
        every { view.showError(any()) } just Runs

        presenter.fetchRepositories()
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchRepositories()
            with(view) {
                showLoading()
                hideLoading()
                getString(errorRes)
                showError("")
            }
        }

        confirmVerified(view, interactor, router)
    }

    @Test
    fun `should filter and show filtered list`() {
        `should fetch public repositories successfully`()

        val filteredList = listOf(
            Repository(
                2,
                "username1",
                "name1",
                "description1"
            )
        )

        every { view.setRepositoriesList(any()) } just Runs

        presenter.filterRepositoriesList("e1")

        verify(exactly = 1) {
            view.setRepositoriesList(filteredList)
        }

        verify(exactly = 2) {
            view.hideEmptyListAlert()
        }

        confirmVerified(view, interactor, router)
    }

    @Test
    fun `should filter and show empty list alert`() {
        `should fetch public repositories successfully`()

        every { view.showEmptyListAlert() } just Runs

        presenter.filterRepositoriesList("eas1")

        verify(exactly = 1) {
            view.showEmptyListAlert()
        }

        confirmVerified(view, interactor, router)
    }

    @Test
    fun `should reset list when clear filter`() {
        `should fetch public repositories successfully`()

        every { view.setRepositoriesList(any()) } just Runs

        presenter.resetList()

        verify(exactly = 2) {
            view.setRepositoriesList(repositories)
        }

        confirmVerified(view, interactor, router)
    }

    @Test
    fun `should fetch repository owner information successfully`() {
        val repository = Repository(
            1,
            "username",
            "name",
            "description"
        )
        val response = RepositoryOwnerInfo(
            "name",
            "profileUrl",
            "avatarUrl"
        )
        val display = RepositoryDetailsDisplay(
            "name",
            "description",
            "name",
            "profileUrl",
            "avatarUrl"
        )

        every { interactor.fetchRepositoryOwnerInfo(any()) } returns Single.just(response)
        every { view.showLoading() } just Runs
        every { view.hideLoading() } just Runs
        every { view.openDetailsBottomSheet(any()) } just Runs

        presenter.fetchOwnerInfo(repository)
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchRepositoryOwnerInfo("username")
            with(view) {
                showLoading()
                hideLoading()
                openDetailsBottomSheet(display)
            }
        }

        confirmVerified(view, interactor, router)
    }

    @Test
    fun `should return error when fetching repository owner information`() {
        val repository = Repository(
            1,
            "username",
            "name",
            "description"
        )

        every { interactor.fetchRepositoryOwnerInfo(any()) } returns Single.error(Throwable())
        every { view.showLoading() } just Runs
        every { view.hideLoading() } just Runs
        every { view.getString(any()) } returns ""
        every { view.showError(any()) } just Runs

        presenter.fetchOwnerInfo(repository)
        testScheduler.triggerActions()

        verify(exactly = 1) {
            interactor.fetchRepositoryOwnerInfo("username")
            with(view) {
                showLoading()
                hideLoading()
                getString(errorRes)
                showError("")
            }
        }

        confirmVerified(view, interactor, router)
    }

    @Test
    fun `should router to user profile fragment`() {
        every { router.routerToUserProfile(any()) } just Runs

        presenter.openUserProfileFragment("")

        verify(exactly = 1) {
            router.routerToUserProfile("")
        }

        confirmVerified(view, interactor, router)
    }
}
