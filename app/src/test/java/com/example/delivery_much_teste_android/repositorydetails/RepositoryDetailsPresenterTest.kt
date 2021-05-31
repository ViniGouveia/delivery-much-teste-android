package com.example.delivery_much_teste_android.repositorydetails

import com.example.delivery_much_teste_android.BaseUnitTest
import com.example.delivery_much_teste_android.ui.repositories.details.RepositoryDetailsContract
import com.example.delivery_much_teste_android.ui.repositories.details.RepositoryDetailsPresenterImpl
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class RepositoryDetailsPresenterTest : BaseUnitTest() {

    private lateinit var presenter: RepositoryDetailsContract.Presenter

    private val view = mockk<RepositoryDetailsContract.View>()

    override fun initialize() {
        presenter = RepositoryDetailsPresenterImpl(view)
    }

    @Test
    fun `assert presenter is not null`() {
        assertNotNull(presenter)

        confirmVerified(view)
    }
}
