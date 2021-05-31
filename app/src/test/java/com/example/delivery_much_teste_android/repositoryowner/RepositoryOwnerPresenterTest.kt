package com.example.delivery_much_teste_android.repositoryowner

import com.example.delivery_much_teste_android.BaseUnitTest
import com.example.delivery_much_teste_android.ui.repositoryowner.RepositoryOwnerContract
import com.example.delivery_much_teste_android.ui.repositoryowner.RepositoryOwnerPresenterImpl
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class RepositoryOwnerPresenterTest : BaseUnitTest() {

    private lateinit var presenter: RepositoryOwnerContract.Presenter

    private val view = mockk<RepositoryOwnerContract.View>()

    override fun initialize() {
        presenter = RepositoryOwnerPresenterImpl(view)
    }

    @Test
    fun `assert presenter is not null`() {
        assertNotNull(presenter)

        confirmVerified(view)
    }
}
