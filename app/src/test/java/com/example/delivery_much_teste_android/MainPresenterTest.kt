package com.example.delivery_much_teste_android

import com.example.delivery_much_teste_android.ui.MainContract
import com.example.delivery_much_teste_android.ui.MainPresenterImpl
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class MainPresenterTest : BaseUnitTest() {

    private lateinit var presenter: MainContract.Presenter

    private val view = mockk<MainContract.Activity>()

    override fun initialize() {
        presenter = MainPresenterImpl(view)
    }

    @Test
    fun `assert presenter is not null`() {
        assertNotNull(presenter)

        confirmVerified(view)
    }
}
